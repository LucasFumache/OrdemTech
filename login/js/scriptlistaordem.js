document.addEventListener('DOMContentLoaded', function() {
    // Atualiza a mensagem de boas-vindas
    const nome = getQueryParam('nome');
    if (nome) {
        document.getElementById('mensagem').textContent = `Bem-vindo(a), ${decodeURIComponent(nome)}!`;
    } else {
        document.getElementById('mensagem').textContent = 'Nome não fornecido.';
    }

    
    const id_cliente = getQueryParam('id_cliente') || 'default_id_cliente';
    pesquisarOS(id_cliente);

   
    const btnpagar = document.getElementById('btnpagar');
    const modal = document.getElementById('janela-modal');
    const closebtn = document.querySelector('.close');
    const btnConfirmarPagamento = document.getElementById('btn-confirmar');

    btnpagar.addEventListener('click', function() {
        console.log("Botão 'Pagar' clicado");
        mostrarModalSeNecessario(); 
    });

    closebtn.addEventListener('click', function() {
        modal.style.display = 'none'; 
        console.log("Fechar modal clicado");
    });

    window.addEventListener('click', function(event) {
        if (event.target == modal) {
            modal.style.display = 'none'; 
            console.log("Clique fora do modal");
        }
    });

    btnConfirmarPagamento.addEventListener('click', function() {
        confirmarPagamento(); 
    });
});

function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

function pesquisarOS(id_cliente) {
    fetch(`http://192.168.1.184:8080/ordem/cliente/${id_cliente}`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "GET"
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('OS não encontrada');
        }
    })
    .then(data => {
        console.log('Dados brutos recebidos:', data);
        if (Array.isArray(data)) {
            exibirDados(data);
        } else {
            exibirDados([data]);
        }
    })
    .catch(error => {
        alert('Erro ao pesquisar: ' + error.message);
    });
}

function formatarData(dataString) {
    const date = new Date(dataString);
    const dia = String(date.getDate()).padStart(2, '0');
    const mes = String(date.getMonth() + 1).padStart(2, '0');
    const ano = date.getFullYear();
    const horas = String(date.getHours()).padStart(2, '0');
    const minutos = String(date.getMinutes()).padStart(2, '0');

    return `${dia}-${mes}-${ano} ${horas}:${minutos}`;
}

function formatarDataentrega(dataString) {
    const date = new Date(dataString);
    const dia = String(date.getDate()).padStart(2, '0');
    const mes = String(date.getMonth() + 1).padStart(2, '0');
    const ano = date.getFullYear();

    return `${dia}-${mes}-${ano}`;
}

let ordensAguardandoPagamento = [];

function exibirDados(dataArray) {
    const div = document.querySelector('.login-container');
    if (!div) {
        console.error('Div não encontrada');
        return;
    }

    div.innerHTML = '';

    const container = document.createElement('div');

    dataArray.forEach(data => {
        
        if (data.situacao === 'Aguardando pagamento!') {
            ordensAguardandoPagamento.push(data);
        }

        const ul = document.createElement('ul');

        const fields = [
            { label: 'Data OS:', value: formatarData(data.data_os) || 'Não disponível' },
            { label: 'Status Serviço:', value: data.status_servico || 'Não disponível' },
            { label: 'Situação:', value: data.situacao || 'Não disponível' },
            { label: 'Aparelho:', value: data.aparelho || 'Não disponível' },
            { label: 'Defeito:', value: data.defeito || 'Não disponível' },
            { label: 'Nome Técnico:', value: data.nome_tecnico || 'Não disponível' },
            { label: 'Data de Entrega:', value: formatarDataentrega(data.data_de_entrega) || 'Não disponível' },
            { label: 'Valor Total:', value: data.valortotal ? `R$ ${data.valortotal}` : 'Não disponível' }
        ];

        fields.forEach(field => {
            const li = document.createElement('li');
            li.innerHTML = `<strong>${field.label}</strong> ${field.value}`;
            ul.appendChild(li);
        });

        container.appendChild(ul);
        container.appendChild(document.createElement('hr'));
    });

    div.appendChild(container);
}

function mostrarModalSeNecessario() {
    if (ordensAguardandoPagamento.length > 0) {
        const modalAparelho = document.getElementById('modal-aparelho');
        const modalValorTotal = document.getElementById('modal-valortotal');

        modalAparelho.innerHTML = '';
        
        ordensAguardandoPagamento.forEach((ordem, index) => {
            const valor = parseFloat(ordem.valortotal) || 0;
            modalAparelho.innerHTML += `
                <div>
                    <input type="checkbox" id="aparelho-${index}" data-valor="${valor}" />
                    <label for="aparelho-${index}">Aparelho: ${ordem.aparelho || 'Não disponível'}</label>
                    <span> - Valor: R$ ${valor.toFixed(2)}</span>
                </div>
                <hr />
            `;
        });

        
        modalValorTotal.innerHTML = `<strong>Valor Total: R$ 0,00</strong>`;

       
        const checkboxes = document.querySelectorAll('#modal-aparelho input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', atualizarValorTotal);
        });

        
        const modal = document.getElementById('janela-modal');
        modal.style.display = 'flex';
    } else {
        alert('Não há ordens de serviço na situação "Aguardando pagamento".');
    }
}

function atualizarValorTotal() {
    const checkboxes = document.querySelectorAll('#modal-aparelho input[type="checkbox"]');
    let valorTotalSelecionado = 0;
    
    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            valorTotalSelecionado += parseFloat(checkbox.getAttribute('data-valor')) || 0;
        }
    });

    const modalValorTotal = document.getElementById('modal-valortotal');
    modalValorTotal.innerHTML = `<strong>Valor Total: R$ ${valorTotalSelecionado.toFixed(2)}</strong>`;
}

function confirmarPagamento() {
    const checkboxes = document.querySelectorAll('#modal-aparelho input[type="checkbox"]');
    let valorTotalSelecionado = 0;
    let ordensSelecionadas = [];

    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            const valor = parseFloat(checkbox.getAttribute('data-valor')) || 0;
            valorTotalSelecionado += valor;
            ordensSelecionadas.push(checkbox.nextElementSibling.textContent.trim());
        }
    });

    if (valorTotalSelecionado > 0) {
        alert(`Você selecionou os seguintes aparelhos para pagamento:\n\n${ordensSelecionadas.join('\n')}\n\nValor Total Selecionado: R$ ${valorTotalSelecionado.toFixed(2)}`);
        window.location.href = 'http://192.168.33.71:5500/login/Qrcode.html'; 
    } else {
        alert('Nenhuma ordem de serviço selecionada.');
    }

    const modal = document.getElementById('janela-modal');
    modal.style.display = 'none'; 
}

const btnSair = document.getElementById('btnsair');
btnSair.addEventListener('click', function() {
    logout();
});

function logout() {
    window.location.href = "http://192.168.1.184:5500/PaginaInicial/PaginaInicial.html";
}

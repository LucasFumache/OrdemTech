document.addEventListener('DOMContentLoaded', function() {
    const nome = getQueryParam('nome');
    if (nome) {
        document.getElementById('mensagem').textContent = `Bem-vindo(a), ${decodeURIComponent(nome)}!`;
    } else {
        document.getElementById('mensagem').textContent = 'Nome não fornecido.';
    }

    const id_tecnico = getQueryParam('id_tecnico') || 'default_id_tecnico';
     
    pesquisarOS(id_tecnico);
});

function getQueryParam(param) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(param);
}

function pesquisarOS(id_tecnico) {
    fetch(`http://192.168.1.184:8080/ordem/tecnico/${id_tecnico}`, {
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
            const div = document.querySelector('.login-container');
            div.innerHTML = 'Sem trabalho por enquanto';
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


    return `${dia}-${mes}-${ano} `;
}

function exibirDados(dataArray) {
    const div = document.querySelector('.login-container');
    if (!div) {
        console.error('Div não encontrada');
        return;
    }


    div.innerHTML = '';


    const container = document.createElement('div');

    dataArray.forEach(data => {
        const ul = document.createElement('ul');

        const fields = [
            { label: 'Data OS:', value: formatarData(data.data_os) || 'Não disponível' },
            { label: 'Status Serviço:', value: data.status_servico || 'Não disponível' },
            { label: 'Situação:', value: data.situacao || 'Não disponível' },
            { label: 'Aparelho:', value: data.aparelho || 'Não disponível' },
            { label: 'Defeito:', value: data.defeito || 'Não disponível' },
            { label: 'Nome cliente:', value: data.cliente.nome || 'Não disponível' },
            { label: 'Data de Entrega:', value: formatarDataentrega(data.data_de_entrega) || 'Não disponível' },
            { label: 'Valor Total:', value: data.valortotal ? `R$ ${data.valortotal}` : 'Não disponível' },
    
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

const btnSair = document.getElementById('btnsair');
function logout() {
    window.location.href = "http://192.168.1.184:5500/PaginaInicial/PaginaInicial.html";
}
btnSair.addEventListener('click', function(event) {
    logout();
});

const cpfInput = document.getElementById('cpflogin');
const senhaInput = document.getElementById('senhalogin');
const loginButton = document.getElementById('logincliente');

function validarCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, ''); 
    if (cpf.length !== 11 || /^(\d)\1{10}$/.test(cpf)) return false;
    let soma = 0, resto;
    for (let i = 1; i <= 9; i++) soma += parseInt(cpf.charAt(i - 1)) * (11 - i);
    resto = (soma * 10) % 11;
    if (resto === 10 || resto === 11) resto = 0;
    if (resto !== parseInt(cpf.charAt(9))) return false;
    soma = 0;
    for (let i = 1; i <= 10; i++) soma += parseInt(cpf.charAt(i - 1)) * (12 - i);
    resto = (soma * 10) % 11;
    if (resto === 10 || resto === 11) resto = 0;
    if (resto !== parseInt(cpf.charAt(10))) return false;
    return true;
}

function logincliente() {
    const cpf = cpfInput.value.replace(/\D/g, ''); 
    const senha = senhaInput.value;

    if (!cpf || !senha) {
        alert("Todos os campos devem ser preenchidos.");
        return;
    }

    if (!validarCPF(cpf)) {
        alert("CPF inválido.");
        return;
    }

    fetch(`http://192.168.1.184:8080/ordem/cliente/cpf/${cpf}`, {
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
            throw new Error('Cliente não encontrado.');
        }
    })
    .then(cliente => {
        if (cliente.senha === senha) {
            limpar();
            const nomecli = encodeURIComponent(cliente.nome);
            const idcli = encodeURIComponent(cliente.id);
            
            window.location.href = `http://192.168.1.184:5500/login/ListaProdutos.html?nome=${nomecli}&id_cliente=${idcli}`;
        } else {
            alert('Senha inválida.');
        }
    })
    .catch(error => {
        alert('Erro ao fazer login: ' + error.message);
    });
}

loginButton.addEventListener('click', function(event){
    logincliente();
});

function limpar() {
    cpfInput.value = "";
    senhaInput.value = "";
};

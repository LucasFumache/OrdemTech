const Inome = document.getElementById('nomeCadastro');
const Icpf = document.getElementById('cpfCadastro');
const Itelefone = document.getElementById('telefoneCadastro');
const Iendereco = document.getElementById('enderecoCadastro');
const Isenha = document.getElementById('senhaCadastro');
const Cadastro = document.getElementById('cadastro');


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

function cadastrar() {
    if (!Inome.value || !Icpf.value || !Itelefone.value || !Iendereco.value || !Isenha.value) {
        alert("Todos os campos devem ser preenchidos.");
        return;
    }
    if (!validarCPF(Icpf.value)) {
        alert("CPF inválido.");
        return;
    }

    fetch("http://192.168.1.184:8080/ordem/cliente", {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify({
            nome: Inome.value,
            cpf: Icpf.value,
            telefone: Itelefone.value,
            endereco: Iendereco.value,
            senha: Isenha.value,
        })
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('CPF já cadastrado');
        }
    })
    .then(data => {
        limpar();
        alert("Cadastro realizado com sucesso. Você será levado a tela login!")
        window.location.href = 'http://192.168.1.184:5500/login/Cliente.html'; 
        
    })
    .catch(error => {
        alert('Erro ao cadastrar usuário: ' + error.message);
    });
}

Cadastro.addEventListener('click', function(event) {
    cadastrar();
});

function limpar(){
    Inome.value = "";
    Icpf.value = "";
    Itelefone.value = "";
    Iendereco.value = "";
    Isenha.value = "";
}








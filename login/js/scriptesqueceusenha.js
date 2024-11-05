const cpfInput = document.getElementById('cpfesque');
const senhaInput = document.getElementById('senhaesque');
const senhaInputc = document.getElementById('senhaesquecon');
const senhaButton = document.getElementById('btnesque');



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

function trocarSenha() {
    const cpf = cpfInput.value;
    const senha = senhaInput.value;
    const senhacon = senhaInputc.value;

    if (!cpf || !senha || !senhacon) {
        alert("Todos os campos devem ser preenchidos.");
        return;
    }

    if (!validarCPF(cpf)) {
        alert("CPF inválido.");
        return;
    }

    if (senha !== senhacon) {
        alert("Coloque as mesmas senhas nos campos!");
    } else {
        fetch(`http://192.168.1.184:8080/ordem/cliente/cpf/${cpf}`, {
            headers: {
                "Accept": "application/json",
                "Content-Type": "text/plain"
            },
            method: "PUT",
            body: senha 
        })
        .then(response => {
            if (response.ok) {
                return response.json(); 
            } else {
                throw new Error('CPF não encontrado.');
            }
        })
        .then(cliente => {
            alert("Atualizado com sucesso");
            console.log('Usuário alterado com sucesso:', cliente);
            limpar();
        })
        .catch(error => {
            alert('Erro ao atualizar usuario: ' + error.message);
        });
    }
}




senhaButton.addEventListener('click', function(evente){
   
    event.preventDefault(); 
    trocarSenha();

});

function limpar(){
    cpfInput.value = "";
    senhaInput.value = "";
    senhaInputc.value = "";
};

document.addEventListener('DOMContentLoaded', carregarPacientes)

function carregarPacientes() {
    fetch('http://localhost:8080/pacientes')
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao buscar pacientes: ");
        }
        return response.json()
    })
    .then(pacientes => {
        const lista = document.getElementById('listaPacientes')
        lista.innerHTML = ''

        if (pacientes.lenght === 0) {
            lista.innerHTML = '<li>Nenhum paciente encontrado. </li>'
            return;
        }

        pacientes.forEach(paciente => {
            const item = document.createElement('li')
            item.innerHTML = `<strong>Nome:</strong> ${paciente.nome}<br>
                            <strong>E-mail:</strong> ${paciente.email}`
            lista.appendChild(item)
        })
    })
    .catch( erro => {
        document.getElementById('listaPacientes').innerHTML = 
        '<li>Erro ao carregar pacientes.</li>'
    })
}

const clienteId = document.getElementById('clientes')
const ordemServicoId = document.getElementById('ordem-servico')




function convertToLi(cliente) {

    return `
            <li>
            <p>ID: ${cliente.id}</p>
            <p>NOME: ${cliente.name} </p>
           
            </li>
                    `
}

                    

function convertToOS(ordemServico) {

    return `
            <li>
            <p>ID: ${ordemServico.id}</p>
            <p>Descrição: ${ordemServico.descricao}</p>
            <p>Preco: ${ordemServico.preco}</p>
            <p>Status: ${ordemServico.status}</p>
            <p>Data Abertura: ${ordemServico.dataAbertura}</p>
            <p>Data Finalizada: ${ordemServico.dataFinalizacao}</p>
            <p>Cliente: ${ordemServico.cliente.name}</p>
        
           
            </li>
                    `
}

fetch('http://localhost:8080/clientes')
    .then(Response => Response.json())
    // .then(Response => console.log(Response))
    .then((clientes = []) => {
        const novaLinha = clientes.map(convertToLi).join('')
        clienteId.innerHTML = novaLinha
    })
    fetch('http://localhost:8080/ordem-servico')
    .then(Response => Response.json())
    .then((ordemServico = []) => {
        const novaLinhaOS = ordemServico.map(convertToOS).join('')
        ordemServicoId.innerHTML = novaLinhaOS
       
    })

       

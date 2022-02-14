
// AJAX GET
function getClients() {
  $("#table-clients").empty();

  setTimeout(() => {
    $.ajax({
      url: "http://localhost:8080/demo/clients/",
      method: 'GET',
      context: document.body,

      success: (data) => {
        clients = data;
        if (data.length == 0) {
          $("#table-clients").append(`
            <tr>
              <td colspan="4">No hay clientes</td>
            </tr>
          `);
        }

        for(client of clients) {
					client.modified == null 
						? client.modified = "-" 
						: client.modified = client.modified
					;

          $("#table-clients").append(`
            <tr id="${client.id}">
              <th scope="row">${client.id}</th>
              <td>${client.name}</td>
              <td>${client.modified}</td>
              <td>${client.created}</td>
              <td><button type="button" class="btn btn-primary put">Editar</button>
              <button type="button" class="btn btn-danger delete">Eliminar</button></td>
            </tr>
          `);
        }

      },
      error: (error) => {
        alert('error: ' + error );
      }
    });
  }, 700)
}

// AJAX DELETE
function deleteClient() {
  let id = $(this).parent().parent().children().eq(0).text();
  let name = $(this).parent().parent().children().eq(1).text();

  localStorage.setItem("deleteId", id);
  localStorage.setItem("deleteName", name);

  let deleteWindow = window.open("./deleteClient.html", "", "width=500, height=500");

  deleteWindow.delete = () => {
    $(this).parent().parent().remove();
  }
}

// AJAX 
function postClient() {
  let postClient = window.open("./postClient.html", "", "width=500,height=500");

  postClient.post = () => {
    let newClient = JSON.parse(localStorage.getItem("newClient"));

    $("#table-clients").append(`
      <tr id="${newClient.id}">
        <th scope="row">${newClient.id}</th>
        <td>${newClient.name}</td>
        <td>-</td>
        <td>${newClient.created}</td>
        <td><button type="button" class="btn btn-primary put">Editar</button></td>
        <td><button type="button" class="btn btn-danger delete">Eliminar</button></td>
      </tr>
    `);
  }
}

// AJAX PUT
function putClient() {
  let id = $(this).parent().parent().children().eq(0).text();
  let name = $(this).parent().parent().children().eq(1).text();
  
  localStorage.setItem("putId", id);
  localStorage.setItem("putName", name);

  let putWindow = window.open("./putClient.html", "", "width=500, height=500");

  putWindow.put = () => {
    let newName = localStorage.getItem("newName");
    let modified = localStorage.getItem("modified");
    $(this).parent().parent().children().eq(1).text(newName);
    $(this).parent().parent().children().eq(2).text(modified);
  }
}

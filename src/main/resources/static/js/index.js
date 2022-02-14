
$(document).ready( () => { 
  const NAME_APP = "Clients App"
  $("#name-app").text(NAME_APP)
  getClients();
});

let clients = null;

$("#refresh").on("click", getClients);

$("#table-clients").on("click", ".delete", deleteClient);

$("#table-clients").on("click", ".put", putClient);

$("#post").on("click", postClient);

//Definición de variables
const url = "http://localhost:8080/usuario/";
const contenedor = document.querySelector("tbody");
let resultados = "";

const modalUsuario = new bootstrap.Modal(
  document.getElementById("modalUsuario")
);
const formUsuario = document.querySelector("form");
const nombre = document.getElementById("nombre");
const email = document.getElementById("email");
const prioridad = document.getElementById("prioridad");
var opcion = "";

btnCrear.addEventListener("click", () => {
  nombre.value = "";
  email.value = "";
  prioridad.value = "";
  modalUsuario.show();
  opcion = "crear";
});

//funcion para mostrar los resultados
const mostrar = (usuarios) => {
  usuarios.forEach((usuario) => {
    resultados += `<tr>
                            <td>${usuario.id}</td>
                            <td>${usuario.nombre}</td>
                            <td id="email_borrar">${usuario.email}</td>
                            <td>${usuario.prioridad}</td>
                            <td class="text-center"><a class="btnEditar btn btn-primary">Editar</a> | <a class="btnBorrar btn btn-danger">Borrar</a> | <a class="btnCorreo btn btn-outline-danger">Borrar correo</a></td>
                       </tr>
                    `;
  });
  contenedor.innerHTML = resultados;
};
document.getElementById("btn_buscar").addEventListener("click", async (e) => {
  e.preventDefault();
  resultados = "";
  const email = document.getElementById("busqueda").value;
  const respuesta = await fetch(`${url}querys?email=${email}`);
  const consulta = await respuesta.json();
  mostrar(consulta);
});

//Procedimiento Mostrar
fetch(url)
  .then((response) => response.json())
  .then((data) => mostrar(data))
  .catch((error) => console.log(error));

const on = (element, event, selector, handler) => {
  //console.log(element)
  //console.log(event)
  //console.log(selector)
  //console.log(handler)
  element.addEventListener(event, (e) => {
    if (e.target.closest(selector)) {
      handler(e);
    }
  });
};

//Procedimiento Borrar
on(document, "click", ".btnBorrar", (e) => {
  const fila = e.target.parentNode.parentNode;
  const id = fila.firstElementChild.innerHTML;
  alertify.confirm(
    "This is a confirm dialog.",
    function () {
      fetch(url + id, {
        method: "DELETE",
      }).then((res) => res.json());
      location.reload();
      //alertify.success('Ok')
    },
    function () {
      alertify.error("Cancel");
    }
  );
});

//Procedimiento Borrar
on(document, "click", ".btnCorreo", (e) => {
  const fila = e.target.parentNode.parentNode;
  const email = document.getElementById("email_borrar").innerHTML;
  alertify.confirm(
    "This is a confirm dialog. " + email,
    function () {
      console.log(`${url}correo/${email}`);
      fetch(`${url}correo/${email}`, {
        method: "DELETE",
      }).then((res) => res.json());
      location.reload();
      //alertify.success('Ok')
    },
    function () {
      alertify.error("Cancel");
    }
  );
});
//Procedimiento Editar
let idForm = 0;
on(document, "click", ".btnEditar", (e) => {
  const fila = e.target.parentNode.parentNode;
  idForm = fila.children[0].innerHTML;
  const nombreForm = fila.children[1].innerHTML;
  const emailForm = fila.children[2].innerHTML;
  const prioridadForm = fila.children[3].innerHTML;
  nombre.value = nombreForm;
  email.value = emailForm;
  prioridad.value = prioridadForm;
  opcion = "editar";
  modalUsuario.show();
});

//Procedimiento para Crear y Editar
formUsuario.addEventListener("submit", (e) => {
  e.preventDefault();
  if (opcion == "crear") {
    //console.log('OPCION CREAR')
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        nombre: nombre.value,
        email: email.value,
        prioridad: prioridad.value,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        const nuevoUsuario = [];
        nuevoUsuario.push(data);
        mostrar(nuevoUsuario);
      });
  }
  if (opcion == "editar") {
    //console.log('OPCION EDITAR')
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id: idForm,
        nombre: nombre.value,
        email: email.value,
        prioridad: prioridad.value,
      }),
    })
      .then((response) => response.json())
      .then((response) => location.reload());
  }
  modalUsuario.hide();
});

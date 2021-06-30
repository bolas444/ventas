<template>
  <v-app>
    <br /><br /><br /><br />
    <v-card class="mx-auto" width="300">
      <v-card-title>Lista de Categorias</v-card-title>
      <v-simple-table fixed-header height="400px">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="i in datos" :key="i.id">
            <td>{{ i.nombre }}</td>
            <td>
              <v-icon small class="mr-2" @click="editItem(i.id)">{{
                icons.mdiPencil
              }}</v-icon>
              <v-icon extra small @click="deleteItem(i.id)"
                >{{ icons.mdiDelete }}
              </v-icon>
            </td>
          </tr>
        </tbody>
      </v-simple-table>
      <v-btn
        width=300
        class=" mx-lg-auto"
        color="teal"
        v-on:click="fn_agregar()"
        >Nuevo</v-btn
      >
    </v-card>

    <v-dialog v-model="dialog" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">Editar</span>
        </v-card-title>

        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  v-model="nombre"
                  label="nombre categoria"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn color="blue darken-1" text @click="editar(id, nombre)"
            >Actualizar</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!--  <CategoriaAgregar  v-if="CategoriaAgregar"></CategoriaAgregar>-->
  </v-app>
</template>

<script>
import axios from "axios";

//import CategoriaAgregar from "./CategoriaAgregar";
import { mdiPencil, mdiDelete } from "@mdi/js";

export default {
  name: "Categorias",

  components: {
    //  CategoriaAgregar,
  },
  data: () => ({
    icons: {
      mdiPencil,
      mdiDelete
    },
    dialog: false,
    datos: [],
    muestra: true,
    id: "",
    nombre: ""
  }),
  methods: {
    editItem(id) {
      this.id = id;
      this.dialog = true;
    },
    editar(id, nombre) {
      axios
        .get("/categoria/editar", {
          params: {
            id: id,
            nombre: nombre
          }
        })
        .then(response => {
          this.info = response.data;
          axios
            .get("/categoria/todos")
            .then(response => (this.datos = response.data));
        })
        .catch(e => {
          alert.error(e);
        });
      this.dialog = false;
    },
    deleteItem(id) {
      axios
        .get("categoria/borrar", {
          params: {
            id: id
          }
        })
        .then(response => {
          this.info = response.data;
          axios.get("/categoria/todos").then(this.fn_mostrar_datos);
        })
        .catch(e => {
          alert.error(e);
        });
    },
    fn_mostrar_datos: function(resp) {
      this.datos = resp.data;
    },
    fn_muestra_pantalla: function() {
      this.muestra = false;
    },
    fn_agregar() {
      this.$router.push("/Categorias/Agregar");
    }
  },
  mounted: function() {
    this.axios({
      method: "get",
      responseType: "json",
      url: "/categoria/todos"
    })
      .then(this.fn_mostrar_datos)
      .catch(function(error) {
        alert(
          "No se pudo terminar el contrato [" + error.response.status + "]"
        );
      });
  }
};
</script>

<style>
</style>
<template>
  <v-app class="mx-auto" max-width="300">    
     <br>
     <br>
     <br>
     <br>
      <div class="titulo">
        <h1>Nueva Categoria</h1>
      </div>
      <div id="app" class="app">
        <div class="formulario">
          <v-form class="form" ref="form" v-model="valid" lazy-validation>
            <v-text-field
              v-model="nombre"
              :counter="50"
              :rules="nameRules"
              label="nombre"
              required
            ></v-text-field>
            <v-btn color="success" class="mr-4" @click="agregarCategoria">
              Guardar
            </v-btn>
            <v-btn color="indigo" class="mr-4" v-on:click="fn_atras()">
              Atras
            </v-btn>
          </v-form>
        </div>
      </div>
    <!-- <Menu />
    <Categorias v-if="Categorias"></Categorias>-->
  </v-app>
</template>
<script>

import axios from "axios";
//import Menu from "./Menu";

//import Categorias from "./Categorias";

export default {
  components: {
    //  Categorias
  //  Menu
  },
  data: () => ({
    datos: [],
    muestra: true,
    marca: ""
  }),
  methods: {
    fn_mostrar_categoria: function(resp) {
      this.usuarios = resp.data;
      console.log("Estoy mostrando " + resp.data);
    },
    agregarCategoria() {
      const formData = new FormData();
      formData.append("nombre", this.nombre);
      axios
        .post("/categoria/nuevo", formData)
        .then(response => {
          this.nombre = "";
          this.info = response.data;
        })
        .catch(e => {
          alert.error(e);
        });
        
        axios.get("/categoria/todos")
         .then(
          this.$router.push("/Categorias"))
      .catch(e => {
          alert.error(e);
        });
    },
    fn_atras() {
      this.$router.push("/Categorias");
    }
  },
  mounted: function() {
    this.axios({
      method: "get",
      responseType: "json",
      url: "/categoria/todos"
    })
      .then(this.fn_mostrar_categoria)
      .catch(function(error) {
        alert("No se pudo mostrar los correos [" + error.response.status + "]");
      });
  }
};
</script>

<style>
</style>
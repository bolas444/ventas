<template>
  <v-app class="mx-auto" max-width="800">
    <br>
     <br>
     <br>
     <br>
    <div v-if="muestra">
      <div class="titulo">
        <h1>Nuevo Producto</h1>
      </div>
      <div id="app" class="app">
        <div class="formulario">
          <v-form class="form" ref="form" lazy-validation>
            <v-text-field
              v-model="nombre"
              :counter="50"
              label="nombre"
              required
            ></v-text-field>
            <v-text-field
              v-model="presentacion"
              :counter="50"
              label="presentacion"
              required
            ></v-text-field>
            <v-text-field
              v-model="cantidad"
              :counter="50"
              label="cantidad"
              required
            ></v-text-field>
            <v-select
              :items="categoria"
              item-text="nombre"
              item-value="id"
              v-model="categoria_id"
              :counter="50"
              label="categoria_id"
              required
            ></v-select>
            <v-text-field
              :items="precio"
              item-text="nombre"
              item-value="id"
              v-model="precio"
              :counter="50"
              label="precio"
              required
            ></v-text-field>
            <v-btn color="success" class="mr-4" @click="agregarProducto">
              Guardar
            </v-btn>
            <v-btn color="indigo" class="mr-4" v-on:click="fn_atras()">
              Atras
            </v-btn>
          </v-form>
        </div>
      </div>
    </div>
   
    <!--<Productos v-if="Productos"></Productos>-->
  </v-app>
</template>
<script>
import axios from "axios";

//import Productos from "./Productos";

export default {
  components: {
    //    Productos
//    Menu
  },
  data: () => ({
    datos: [],
    muestra: true,
    nombre: "",
    presentacion: "",
    cantidad: "",
    categoria_id: "",
    precio: "",
    categoria: []
  }),
  methods: {
    fn_mostrar_Producto: function(resp) {
      this.categoria = resp.data;
      console.log("Estoy mostrando " + resp.data);
    },
    agregarProducto() {
      const formData = new FormData();
      formData.append("nombre", this.nombre);
      formData.append("presentacion", this.presentacion);
      formData.append("cantidad", this.cantidad);
      formData.append("categoria_id", this.categoria_id);
       formData.append("precio", this.precio);
      axios
        .post("/producto/nuevo", formData)
        .then(response => {
          this.nombre = "";
          this.presentacion = "";
          this.cantidad = "";
          this.categoria_id = this.categoria;
          this.precio = "";
          this.info = response.data;
        })
        .catch(e => {
          alert.error(e);
        });
         axios.get("/productos/todos")
         .then(
      this.$router.push("/Productos"))
      .catch(e => {
          alert.error(e);
        });
    },

    fn_atras() {
      this.$router.push("/Productos");
    }
  },
  mounted: function() {
    axios
      .get("/categoria/todos")
      .then(response => (this.categoria = response.data));
  }
};
</script>

<style>
</style>
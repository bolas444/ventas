<template>
  <v-card class="mx-auto" max-width="400" v-if="mostrar">
    <v-img
      class="white--text align-end"
      height="200px"
      src="https://elporvenir.blob.core.windows.net.optimalcdn.com/images/2020/08/10/4_2.jpg"
    >
    <v-card-title>Login</v-card-title>
    </v-img>

    <v-card-text class="text--primary"></v-card-text>
    <v-col cols="12" sm="20" md="20">
      <v-card-text>Correo</v-card-text>
      <v-text-field
        name="correo"
        v-model="correo"
        placeholder="ejemplo@gmail.com"
      >
      </v-text-field>
    </v-col>
    <v-col cols="12" sm="30" md="30">
      <v-card-text>Contraseña</v-card-text>
      <v-text-field
        name="pass"
        v-model="pass"
        type="password"
        counter
      ></v-text-field>
    </v-col>

    <v-card-actions>
      <v-btn color="success" @click="fn_verifica_usuario(correo, pass)"
        >Ingresar</v-btn
      >
    </v-card-actions>
  </v-card>
  <!--  <Menu v-else-if="!mostrar"></Menu>-->
</template>

  

<script>
import axios from "axios";
//import Menu from "./Menu";

export default {
  name: "LoginForm",
  components: {
    //Menu,
  },
  data: () => ({
    mostrar: true,
    usuarios: [],
    log: "",
    correo: [],
    pass: []
  }),
  methods: {
    fn_mostrar_usuarios: function(resp) {
      this.usuarios = resp.data;
      console.log("Estoy mostrando usuarios" + resp.data);
    },
    fn_verifica_usuario(correo, pass) {
      /* for (var k = 0; k < this.usuarios.length; k++) {
        if (
          this.usuarios[k].correo == this.login.correo &&
          this.usuarios[k].pass == this.login.pass
        ) {
          this.mostrar = false;
        }
      }*/
      axios
        .get("/usuario/ingresar", {
          params: {
            correo: correo,
            pass: pass
          }
        })
        .then(response => {
          this.log = response.data;
          if (this.log) {
            this.mostrar = false;

            alert("Bienvenido");
          this.$router.push("/Categorias");

}
          if (this.mostrar == true) {
            alert("Usuario y/o Contraseña incorrecta");
          }
        })
        .catch(error => {
          console.log(error.response);
          alert("ingrese usuario y/o Contraseña");
        });
    }
  },
  mounted: function() {
    this.axios({
      method: "get",
      responseType: "json",
      url: "/usuario/todos"
    })
      .then(this.fn_mostrar_usuarios)
      .catch(function(error) {
        alert("No se pudo mostrar los correos [" + error.response.status + "]");
      });
  }
};
</script>

<style>
</style>
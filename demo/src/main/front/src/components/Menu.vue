<template>
  <v-app >
        <v-navigation-drawer
          v-model="drawer"
          :clipped="$vuetify.breakpoint.lgAndUp"
          app
        >
          <v-list dense>
              <v-list>
                <v-list-item link>  
                  <v-icon medium >
                  {{ icons.mdiShape }}</v-icon>
                  <v-list-item-content v-on:click="fn_categorias()">
                    <center> Categorias </center>
                  </v-list-item-content>
                </v-list-item>

                <v-list-item link>
                   <v-icon medium >
                  {{ icons.mdiBallot}}</v-icon>
                    <v-list-item-content v-on:click="fn_productos()">
                    <center>Productos</center>
                    </v-list-item-content>
                </v-list-item>

                <v-list-item link>
                   <v-icon medium >
                  {{ icons.mdiCalendar}}</v-icon>
                    <v-list-item-content v-on:click="fn_ventas()">
                    <center>Ventas</center>
                    </v-list-item-content>
                </v-list-item>
              </v-list>

          </v-list>
        </v-navigation-drawer>

        <v-app-bar :clipped-left="$vuetify.breakpoint.lgAndUp"  app color="indigo darken-1" dark>
          <v-toolbar-title style="width: 100px" class="ml-0 pl-3">
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
            <span class="hidden-sm-and-down"></span>
          </v-toolbar-title>
        </v-app-bar>
   <v-main>
         
        </v-main>
  </v-app>
</template>
<script>
//import Categorias from "./Categorias";
//import Productos from "./Productos";
import { mdiShape,mdiBallot,mdiCalendar } from "@mdi/js";

export default {
  //name: "Menu",

  components: {
    //Categorias,
    //Productos
  },

  data: () => ({

    
      icons: {
      mdiShape,
      mdiBallot,
      mdiCalendar
      },
      drawer: null,
      
      m_categorias: false,
      m_productos: false
      }),

  methods: {
    fn_categorias() {
      this.$router.push("/Categorias");
    },
    fn_productos() {
      this.$router.push("/Productos");
    },
    fn_ventas(){
      this.$router.push("/Ventas");
    },
  }
};
</script>


<template>

<v-app>
<router-view/>     
<transition mode="out-in">
<Menu v-if="$route.path!=='/'"/> 
         
</transition>
  </v-app>

</template>

<script>
import Menu from "./components/Menu"

export default {
  name: 'App',

  components: {
    Menu
  },

  data: () => ({
    //

  }),
};
</script>

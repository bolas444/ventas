import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import Router from 'vue-router';
import LoginForm from './components/LoginForm'
import ProductoAgregar from './components/ProductoAgregar'
import Productos from './components/Productos'
import Ventas from './components/Ventas'
//import Helloworld from './components/HelloWorld'
import Categorias from './components/Categorias'
import CategoriaAgregar from './components/CategoriaAgregar'

import vuetify from './plugins/vuetify';

Vue.use(Router)

var router = new Router({
  routes:[
    {
      path: '/',
      component: LoginForm
    },
    {
      path: '/Ventas',
      component: Ventas
    },
    {
      path: '/Productos',
      component: Productos
    },
    {
      path: '/Productos/Agregar',
      component: ProductoAgregar
    },
    {
      path: '/Categorias',
      component: Categorias
    },
    {
      path: '/Categorias/Agregar',
      component: CategoriaAgregar
    },
    /*{
      path: '/login',
      component: LoginForm
    }*/
  ]
});

Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')

<template>
  <v-app>
    <br /><br /><br /><br /><br />
    <v-card class="mx-auto" max-width="660">
      <v-card-title>Lista de Productos</v-card-title>
      <v-btn @click="mproductos()">
        <v-badge left color="blue">
          <span slot="badge">{{ totalproducto }}</span>
          <v-icon @click="mproductos()">{{ icons.mdiCart }}</v-icon>
        </v-badge>
      </v-btn>
      <v-simple-table fixed-header height="400px">
        <thead>
          <tr>
            <!--<th class="text-left">id</th>!-->
            <th class="text-left">Nombre</th>
            <th class="text-left">Presentacion</th>
            <th class="text-left">Status</th>
            <th class="text-left">Cantidad</th>
            <th class="text-left">Categoria</th>
            <th class="text-left">Precio</th>
            <th>Acciones</th>
            <th><center>Agregar al carrito</center></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="i in datos" :key="i.id">
            <!--<td >{{i.id}}  </td>!-->
            <td>{{ i.nombre }}</td>
            <td>{{ i.presentacion }}</td>
            <td>{{ i.status }}</td>
            <td>{{ i.cantidad }}</td>
            <td>{{ i.categoria }}</td>
            <td>$ {{ i.precio }}</td>
            <td>
              <v-icon extra small class="mr-2" @click="editItem(i.id)">{{
                icons.mdiPencil
              }}</v-icon>
              <v-icon extra small @click="deleteItem(i.id)"
                >{{ icons.mdiDelete }}
              </v-icon>
            </td>
            <td>
              <v-icon Medium @click="comprarItem(i.id, i.precio, i.nombre)">
                {{ icons.mdiCartPlus }}</v-icon
              >
            </td>
          </tr>
        </tbody>
      </v-simple-table>
      <v-btn
        style="width: 660px"
        class="mr-4"
        color="success"
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
                <v-text-field v-model="nombre" label="nombre"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  v-model="presentacion"
                  label="presentacion"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field
                  v-model="cantidad"
                  label="cantidad"
                ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select
                  :items="categoria"
                  item-text="nombre"
                  item-value="id"
                  v-model="categoria_id"
                  label="categoria"
                  required
                  outlined
                ></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field v-model="precio" label="precio"></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            color="blue darken-1"
            text
            @click="
              editar(id, nombre, presentacion, cantidad, categoria_id, precio)
            "
            >Actualizar</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
    <!--<ProductoAgregar  v-if="ProductoAgregar"></ProductoAgregar>-->
    <v-dialog v-model="dialog2" max-width="310px">
      <v-card>
        <v-card-text v-if="!carrito.length" class="text-center elevation-2 pa-12 headline"> Carrito vacio </v-card-text>
        <div  v-if="carrito.length">
        <v-card-title class="font-weight-black"> Carrito </v-card-title>
          <v-simple-table fixed-header  height="160px">
            <thead>
          <tr>
            <th class="text-left">nombre</th>
            <th class="text-left">precio</th>
            <th class="text-left">cantidad</th>
            <th>Eliminar de la lista</th>
          </tr>
            </thead>
          <tbody>
            <tr id=ids v-for="(item,i) in carrito2" :key="item">
              <td>{{ item.nombre }}</td>
              <td >${{ item.precio }}</td>
              <td>{{ item.count }}</td>
              <td class="text-center"><v-icon @click="quitarItem(item.idn,item.nombre, item.precio, carrito,item,i)">
                {{ icons.mdiCartMinus }}</v-icon></td>
            </tr>
          </tbody>
          </v-simple-table>
          <v-card-text class="text-center">
           cantidad total: {{ totalproducto }} <br />precio: ${{ precio }} <br />
           </v-card-text>
        <v-col class="d-flex" cols="8 ">
          <v-select
            :items="descuento"
            v-model="descuentoo"
            label="descuento"
            outlined
            @input="prueba()"
          ></v-select>
        </v-col>
        <tr>
        <v-col class="d-flex" cols="16" >
          <v-text-field
            v-model="descuentom"
            :rules="rules"
            width="660"
            label="otro %"
            type=number
            min=1
            max=99
            outlined
            oninput="if(Number(this.value) > Number(this.max)) this.value = this.max;"
          >
          </v-text-field>
          <v-card-actions>
          <v-btn :disabled="!descuentom" @click="dm()"> Aplicar</v-btn> 
          </v-card-actions>                  
          </v-col>
          <v-btn  style="width: 310px" color="indigo darken-2" dark @click="comprar()">Comprar</v-btn>
        </tr>
      </div>
      </v-card>
    </v-dialog>
  </v-app>
</template>

<script>
import axios from "axios";
//import Menu from "./Menu";
//import ProductoAgregar from "./ProductoAgregar";
import {  mdiPencil,  mdiDelete,  mdiCart,  mdiCartPlus,  mdiCartMinus} from "@mdi/js";

export default {
  name: "Productos",

  components: {
    //    Menu
    //    ProductoAgregar
  },
  data: () => ({
    icons: {
      mdiPencil,
      mdiDelete,
      mdiCart,
      mdiCartPlus,
      mdiCartMinus
    },
    rules:  [v => !!v || "campo requerrido entre 1-99",v => ( v && v >= 1 ) || "mayor a 0", v => ( v && v <= 99 ) || "el porcentaje maximo es 99"],
    dialog: false,
    dialog2: false,
    datos: [],
    muestra: true,
    id: "",
    idn: 0,
    nombre: "",
    presentacion: "",
    cantidad: "",
    categoria_id: "",
    precio: "",
    categoria: [],
    contador: 1,
    totalproducto: 0,
    a :"",
    descuento: [
      "sin descuento","5","10","15","20","25","30","50","55","60","65","70","75"
    ],
    descuentoo: 0,
    precioi: 0,
    x: 0,    
    y: 0,
    count: 0,
    nactual: 0,
    preciod: "",
    descuentom: "",
    fecha: new Date(),
    carrito: [],
    carrito2: [],
    result: []
  }),
  methods: {
    editItem(id) {
      this.id = id;
      this.dialog = true;
      axios
        .get("/categoria/todos")
        .then(response => (this.categoria = response.data));
    },
    editar(id, nombre, presentacion, cantidad, categoria_id, precio) {
      axios
        .get("/categoria/todos")
        .then(response => (this.categoria = response.data));
      axios
        .get("/producto/editar", {
          params: {
            id: id,
            nombre: nombre,
            presentacion: presentacion,
            cantidad: cantidad,
            categoria_id: categoria_id,
            precio: precio
          }
        })
        .then(response => {
          this.info = response.data;
          axios
            .get("/producto/todos")
            .then(response => (this.datos = response.data));
        })
        .catch(e => {
          alert.error(e);
        });
      this.dialog = false;
      this.fn_mostrar_datos;
    },
    deleteItem(id) {
      axios
        .get("producto/borrar", {
          params: {
            id: id
          }
        })
        .then(response => {
          this.info = response.data;
          axios.get("/producto/todos").then(this.fn_mostrar_datos);
        })
        .catch(e => {
          alert.error(e);
        });
    },
    quitarItem(idn,nombre, precio,carrito,item,i) {
      carrito.splice(i, 1);      
      
      this.precio = precio;
      this.precio = this.precioi - this.precio;
      this.precioi = this.precio;

      this.preciod = precio;
      this.totalproducto = this.carrito.length;

      this.carrito2.splice(0, this.carrito2.length);  

      for( this.x = 0; this.x < this.carrito.length; this.x++){
          this.count=0
        for( this.y = 0; this.y < this.carrito.length; this.y++){
            if(this.carrito[this.x].nombre==this.carrito[this.y].nombre){
              this.count++
            }
        }this.carrito2.push({
            id: this.carrito[this.x].id,
            nombre: this.carrito[this.x].nombre,
            precio: this.carrito[this.x].precio,
            count : this.count
        })
    } 


    for( this.x = 0; this.x < this.carrito2.length; this.x++){
        for( this.y = 0; this.y < this.carrito2.length; this.y++){
          if(this.carrito2[this.x].nombre==this.carrito2[this.y].nombre && this.x!=this.y){
            this.carrito2.splice(this.y, 1) 
          }
        }
    }    
    },
    comprarItem(id, precio, nombre) {
      this.carrito.push({
        id:id,
        nombre: nombre,
        precio: precio
      }),
      this.nombre = nombre;
      this.precio = precio;
      this.precio = this.precioi + this.precio;
      this.precioi = this.precio;
      this.preciod = precio;

      this.totalproducto = this.carrito.length;

    },
    comprar() {
      var pcp= '';
       for( this.x = 0; this.x < this.carrito2.length; this.x++){
        pcp = pcp+ this.carrito2[this.x].count + " " + this.carrito2[this.x].nombre + " a " +this.carrito2[this.x].precio + " pesos. "
       }

     // alert(pcp + " se vendieron "+ this.totalproducto + " con descuento de "+ this.descuentoo + "% y el total fue  "+ this.precio )
      const formData = new FormData();
      formData.append("productos", pcp);
      formData.append("cantidadtotal", this.totalproducto);
      formData.append("descuento", this.descuentoo);
      formData.append("precio", this.precio);
       formData.append("fecha", this.fecha);
      axios
        .post("/ventas/nuevo", formData)
        .then(response => {
          this.productos = "";
          this.cantidadtotal = "";
          this.descuento = "";
          this.precio = "";
          this.fecha = "";
          this.info = response.data;
        })
        .catch(e => {
          alert.error(e);
        });
      this.carrito.splice(0, this.carrito.length);       
      this.carrito2.splice(0, this.carrito2.length);     
      this.totalproducto = this.carrito.length;

    },
    mproductos() {
      this.dialog2 = true;
      this.carrito2.splice(0, this.carrito2.length);  

      for( this.x = 0; this.x < this.carrito.length; this.x++){
          this.count=0
        for( this.y = 0; this.y < this.carrito.length; this.y++){
            if(this.carrito[this.x].nombre==this.carrito[this.y].nombre){
              this.count++
            }
        }this.carrito2.push({
            id: this.carrito[this.x].id,
            nombre: this.carrito[this.x].nombre,
            precio: this.carrito[this.x].precio,
            count : this.count
        })
    } 
    
    for( this.x = 0; this.x < this.carrito2.length; this.x++){
        for( this.y = 0; this.y < this.carrito2.length; this.y++){
          if(this.carrito2[this.x].nombre==this.carrito2[this.y].nombre && this.x!=this.y){
            this.carrito2.splice(this.y, 1) 
          }
        }
    }    

    
/*  hashtable
    localstorage en el cliente y en elservidor 

    var items = {

    }
    items ['25'] = 1;
    if(items['26']){
      items['26']+=1;
    }
    else {
      items['26']=1;
    }
    */
    },
    dm() {
      this.precio = this.precioi;
      if (
        this.descuentom == 1 ||
        this.descuentom == 2 ||
        this.descuentom == 3 ||
        this.descuentom == 4 ||
        this.descuentom == 5 ||
        this.descuentom == 6 ||
        this.descuentom == 7 ||
        this.descuentom == 8 ||
        this.descuentom == 9
      ) {
        this.preciod = this.precio * (".0" + this.descuentom);
        this.precio = this.precio - this.preciod;
      } else {
        this.preciod = this.precio * ("." + this.descuentom);
        this.precio = this.precio - this.preciod;
      }
    },
    prueba() {
      if (this.descuentoo == "sin descuento") {
        this.precio = this.precioi;
      } else if (this.descuentoo == "5") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.05;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "10") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.1;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "15") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.15;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "20") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.2;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "25") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.25;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "30") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.3;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "50") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.5;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "55") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.55;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "60") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.6;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "65") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.65;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "70") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.7;
        this.precio = this.precio - this.preciod;
      } else if (this.descuentoo == "75") {
        this.precio = this.precioi;
        this.preciod = this.precio * 0.75;
        this.precio = this.precio - this.preciod;
      }
    },
    fn_mostrar_datos: function(resp) {
      this.datos = resp.data;
    },
    fn_muestra_pantalla: function() {
      this.muestra = false;
    },
   
    fn_agregar() {
      this.$router.push("/Productos/Agregar");
    }
  },
  mounted: function() {
    this.axios({
      method: "get",
      responseType: "json",
      url: "/producto/todos"
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
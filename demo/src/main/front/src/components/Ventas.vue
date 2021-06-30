<template>
<div class="mx-auto" width="800">
<br/><br/><br/><br/>
<h1> Historial de ventas</h1>
<br/>
    <v-row class="mx-auto" width="900" >
    <v-col
      cols="12"
      sm="6"
    >
      <v-date-picker
      locale="es" 
        :max = "fechamax"
         v-model="dates"
        range
      ></v-date-picker>
    </v-col>
    <v-col
      cols="12"
      sm="6"
    >
      <v-text-field
        v-model="dateRangeText"
        label="Date range"
        prepend-icon="mdi-calendar"
        readonly
      ></v-text-field>
       <v-btn @click="mostrar()">Ver lista</v-btn>
    </v-col>
  </v-row>
  <v-dialog v-model="dialog" max-width="800px">
  <v-card class="mx-auto" width="800">
      <v-simple-table fixed-header height="400px">
        <thead>
          <tr>
           <th>  productos </th>
           <th>cantidad total</th>
           <th>porcentaje descuento</th>
          <th>precio final</th>
          <th>fecha de compra </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="i in datos" :key="i.id">
           <td>{{ i.productos }}</td>
           <td>{{ i.cantidadtotal }}</td>
           <td>{{ i.descuento }}%</td>
          <td>${{ i.precio }}</td>
          <td>{{ i.fecha }}</td>
          </tr>
        </tbody>
      </v-simple-table>
    </v-card>
     </v-dialog>
</div>
</template>

<script>
import axios from "axios";

    export default {
    data: () => ({
        dates: ['',''],
        fechamax: new Date().toISOString().substr(0, 10),
        datos: [],
        fecha1: "",
        fecha2: "",
        fecha:"",
        fechal:"",
        dialog: false,
    }),    
    computed: {
      dateRangeText () {
        return this.dates.join(' ~ ') 
      },
    },
    methods: {
   
    mostrar() {    
            this.dialog=true
    // this.fecha1 = new Date(this.dates[0]).toISOString()
//    this.fecha2 = new Date(this.dates[1]).toISOString()
        if(this.dates[1]==null){
           //alert("no hay 2da fecha");    
     this.fecha1 = new Date(this.dates[0]).toISOString()
            axios
            .get("/ventas/lista1", {
            params: {
                fecha: this.fecha1
            }
            })
            .then(response => {
          this.info = response.data;
          axios
            .get("/ventas/lista1", {
            params: {
                fecha: this.fecha1,
            }
            })
            .then(response => (this.datos = response.data)); 
            
        })
        .catch(e => {
          alert.error(e);
        });
        }else if(this.dates[1]!=null){
           // alert("sihay");
            this.fecha1 = new Date(this.dates[0]).toISOString()      
            this.fecha2 = new Date(this.dates[1]).toISOString()
             if (this.fecha1> this.fecha2){
             var aux=this.fecha1
             this.fecha1=this.fecha2
             this.fecha2=aux
            }
            axios
            .get("/ventas/lista2", {
            params: {
                fecha: this.fecha1,
                fechal: this.fecha2
            }
            })
            .then(response => {
          this.info = response.data;
          axios
            .get("/ventas/lista2", {
            params: {
                fecha: this.fecha1,
                fechal: this.fecha2
            }
            })
            .then(response => (this.datos = response.data)); 
            
        })
        .catch(e => {
          alert.error(e);
        });
        }
    } 
    },
    fn_mostrar_datos: function(resp) {
      this.datos = resp.data;
  }
 }
</script>

<style>

</style>
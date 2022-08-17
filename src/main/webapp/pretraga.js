
     const Pretraga={
        data(){
          const knjige=[];
          const naziv="";
             return{knjige,naziv}
        },

        methods:{
          getKnjige(){
             get("/api/pretraga",{naziv:this.$route.params.naziv},(data)=>{
               this.knjige=data
         })
      },
          detaljnije(nesto){
             router.push("/detaljnije/"+nesto);
           }
     },


         mounted(){
           this.getKnjige();
     },
         watch:{
           $route(to,from){
           this.getKnjige();
        }
     },

     template:`

     <div class="page">
        <section class="knjige" id="knjige">

        <h1 class="heading"><span>Pronadjeno</span></h1>

        <div class="slider" v-for="k in knjige">

          <div class="box">

          <div class="image">
                <img style="width:150px" :src="'/api/slika?id='+k.knj_id">
          </div>

          <div class="content">
               <h3>{{ k.knj_naziv }}</h3>
               <div class="cena"><span>{{ k.knj_cena }} RSD</span></div>
               <button class="btn" @click="detaljnije(k.knj_id)">Detaljnije</button>
          </div>

          </div>

         </div>
      </section>
</div>
    `
}
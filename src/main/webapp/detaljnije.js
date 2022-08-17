
    const Detaljnije = {
       data(){
         const detaljnije=[];
         const odakle="";
         const komentar="";
         const ocena="";
             return{detaljnije,odakle,komentar,ocena}
      },

       methods:{
       omiljene(kid){
           get("/api/dodajOmiljene",{id:kid},(data)=>{
              if (data.res == "ok"){
                alert("Uspesno ste dodali u omiljene");
              }else{
                alert("Greska. Moguce da se vec nalazi u omiljenim.");
           }
         });
       },
         getDetaljnije(){
            odakle = this.$route.params.odakle;
           get("/api/detaljnije",{id:odakle},(data)=>{
               this.detaljnije=data;
          });
      },

         addComment(prom){
           get("/api/komentar",{komentar:this.komentar,ocena:this.ocena,id:prom},(data)=>{
              alert(data.res);

           })
         }
       },
       mounted(){
          this.getDetaljnije();

      },

        template: `

       <div>
         <div class="det" v-for="k in detaljnije">
          <aside>
           <div class="images">
             <img :src="'/api/slika?id='+k.knj_id">
           </div>
          </aside>

        <section>
           <article>
           <div class="info">
             <h2> {{ k.knj_naziv }}</h2><br>
             <p><strong>Autor:</strong> {{ k.aut_naziv }}</p>
             <p><strong>Izdavac:</strong> {{ k.izd_naziv }}</p>
             <p><strong>ISBN:</strong> {{ k.knj_isbn }}</p>
             <p><strong>Cena:</strong> {{ k.knj_cena }}RSD</p>
             <p><strong>Jezik:</strong> {{ k.jez_naziv }}</p>
             <p><strong>Opis:</strong><p> {{ k.knj_opis }}</p>
             <h2>Dodaj u omiljene  <button @click="omiljene(k.knj_id)" class="btn"><i class="fas fa-heart"></i></button></h2>
           </div>

       <div class="comment-box">
        <div class="comment">
          <h2>Ostavi komentar</h2>
             <textarea v-model="komentar"  rows="15" cols="80" placeholder="Unesi komentar" required></textarea>
             <select v-model="ocena">
               <option value="">Izaberite ocenu</option>
               <option value="1">&bigstar;</option>
               <option value="2">&bigstar;&bigstar;</option>
               <option value="3">&bigstar;&bigstar;&bigstar;</option>
               <option value="4">&bigstar;&bigstar;&bigstar;&bigstar;</option>
               <option value="5">&bigstar;&bigstar;&bigstar;&bigstar;&bigstar;</option>
             </select><br>
        <button type="button" class="bttn" @click="addComment(k.knj_id)">POSALJI</button>
        </div>

        <table>
                   	   <tr>
                   		<th>KOMENTAR</th>
                   		<th>OCENA</th>

                   	  </tr>
                   	  <tr v-for="n in k.Komentari">
                   		<td>{{ n.kom_komentar }}</td>
                   		<td>{{ n.kom_ocena }}</td>
                   	 </tr>
                   	</table>

     </div>
  </article>
 </section>
</div>
</div>
        `
}
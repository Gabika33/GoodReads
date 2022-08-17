
    const Onama={
      data(){
        const onama=[];
        return{onama}
      },

      methods:{
        getOnama(){
       }
     },

      mounted(){
        this.getOnama();
     },

     template:`
     <div class="page">
        <section class="o_nama" id="o_nama">

        <h1 class="heading"><span>O nama</span></h1>
           <div class="column">
             <p>Poceli smo sa radom aprila 2010.godine.Od pocetka smo se istakli dobro organizovanim timom koji je imao sluha za potrebe i zelje korisnika sto je doprinelo ostvarenju i nasem neospornom napredovanju.<br><br>
               Sada mozemo da se pohvalimo brojnim pozitivnim komentarima, a ono cime se posebno ponosimo je to sto smo u Vama, kao nasim “saradnicima”, pokrenuli kreativnu stranu i dobili dobre ideje i sugestije.<br><br>
               Treba iskoristiti ovu tehnolosku revoluciju i raditi na svom usavrsavanju. Digitalne knjige su nam na dohvat ruke, i za svaciji ukus postoje odgovarajuce knjige.Ovaj sajt vam donosi mnostvo knjiga koje mozete komentarisati i preporuciti,bilo da trazite strucnu literaturu,ili preferirate zabavne sadrzaje.Pridruzite nam se na sajtu i podelite svoja iskustva ili preporucite neku knjigu koju ste procitali i koja vas je na neki nacin pokrenula ili odusevila.</p>

                <img src="img/pozadina.png" width=100%/>
           </div>
       </section>
     </div>
     `
}
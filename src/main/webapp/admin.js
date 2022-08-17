

    const Admin ={
       data(){
        const admin=[];
        const knjige=[];
        const korisnici=[];
        const komentari=[];
        const zanrovi=[];
        const izdavaci=[];
        const jezici=[];
        const autori=[];
        const pocetna="";
        const nesto = false;
           return{admin,pocetna,nesto}
     },

     methods:{

     },

     mounted(){
        get('/api/admin',{},(data)=>{
           if(data.res=="ok"){
              this.pocetna=data.pocetna;
              this.knjige=data.knjige;
              this.korisnici=data.korisnici;
              this.komentari=data.komentari;
              this.zanrovi=data.zanrovi;
              this.izdavaci=data.izdavaci;
              this.jezici=data.jezici;
              this.autori=data.autori;
              this.nesto=true;
                console.log(data);

          }else if(data.res == "err"){
             router.push("/login");

          }else{
             this.pocetna=data.pocetna;
          }
       });
     },

    template:`

      <div class="page">
        <section v-if="!nesto" class="admin" id="admin">
         <h1 class="heading"><span>Admin</span></h1>
         <span>Ucitavanje<i class="fas fa-spinner"></i></span>
       </section>

         <section v-if="nesto">
          <div class="admin">
           <h2>KNJIGE</h2><br>
           <a href="/dodaj?koga=knjige" class="code"><i class="fas fa-plus-circle"></i>DODAJ</a>
             <table>
           	  <tr>
           	    <th>ID</th>
           	    <th>NAZIV</th>
           		<th>ISBN</th>
           		<th>SLIKA</th>
           		<th>OPIS</th>
           		<th>CENA</th>
           		<th colspan="2">ACTION</th>
           	  </tr>

           	  <tr v-for="k in knjige">
           		<td>{{ k.knj_id }}</td>
           		<td>{{ k.knj_naziv }}</td>
           		<td>{{ k.knj_isbn }}</td>
           		<td><img style="width:150px" :src="'/api/slika?id='+k.knj_id"></td>
           		<td>{{ k.knj_opis }}</td>
           		<td>{{ k.knj_cena }}</td>
           		<td><a v-bind:href="'/dodaj?koga=knjige&edit=d&id=' + k.knj_id + '&naziv=' + k.knj_naziv + '&isbn=' + k.knj_isbn + '&opis=' + k.knj_opis + '&cena=' + k.knj_cena + '&IDjezik=' + k.jez_id + '&IDizdavac=' + k.izd_id" style="background:lightgreen"><i class="fas fa-edit"></i>IZMENI</a></td>
           		<td><a v-bind:href="'/api/admin?com=d&id=' + k.knj_id + '&odakle=knjige'" style="background:red"><i class="fas fa-trash-alt"></i>OBRISI</a></td>
           		</tr>
           </table>

         <h2>KORISNICI</h2>
           <a href="/dodaj?koga=korisnici" class="code"><i class="fas fa-plus-circle"></i>DODAJ</a>
           	<table>
              <tr>
           		<th>ID</th>
           		<th>USERNAME</th>
           		<th>PASSWORD</th>
           		<th>IME</th>
           		<th>PREZIME</th>
           		<th>EMAIL</th>
           		<th>SLIKA</th>
           		<th>TIP</th>
           		<th colspan="2">ACTION</th>
           	  </tr>

              <tr v-for="k in korisnici">
           	    <td>{{ k.kor_id }}</td>
           	    <td>{{ k.kor_username }}</td>
           	    <td>{{ k.kor_password }}</td>
           	    <td>{{ k.kor_ime }}</td>
           	    <td>{{ k.kor_prezime }}</td>
           	    <td>{{ k.kor_email }}</td>
           	    <td><img style="width:150px" :src="'/api/loginslika?id='+k.kor_id"></td>
           	    <td>{{ k.kor_admin }}</td>
           	    <td><a v-bind:href="'/dodaj?koga=korisnici&edit=d&id=' + k.kor_id + '&user=' + k.kor_username + '&password=' + k.kor_password + '&ime=' + k.kor_ime + '&prezime=' + k.kor_prezime + '&email=' + k.kor_email + '&slika=' + k.kor_slika + '&tip=' + k.kor_admin" style="background:lightgreen"><i class="fas fa-edit"></i>IZMENI</a></td>
           	    <td><a v-bind:href="'/api/admin?com=d&id=' + k.kor_id + '&odakle=korisnici'" style="background:red;width:100%"><i class="fas fa-trash-alt"></i>OBRISI</a></td>
             </tr>
           </table>

           <h2>KOMENTARI</h2>
            <a href="/dodaj?koga=komentari" class="code"><i class="fas fa-plus-circle"></i>DODAJ</a>
           	 <table>
           	   <tr>
           		<th>ID</th>
           		<th>KOMENTAR</th>
           		<th>OCENA</th>
           		<th>IDKORISNIKA</th>
           		<th>IDKNJIGE</th>
           		<th colspan="2">ACTION</th>
           	  </tr>
           	  <tr v-for="k in komentari">
           		<td>{{ k.kom_id }}</td>
           		<td>{{ k.kom_komentar }}</td>
           		<td>{{ k.kom_ocena }}</td>
	            <td>{{ k.kor_id }}</td>
           		<td>{{ k.knj_id }}</td>
           		<td><a v-bind:href="'/dodaj?koga=komentari&edit=d&id=' + k.kom_id + '&komentar=' + k.kom_komentar + '&ocena=' + k.kom_ocena  + '&IDkorisnika=' + k.kor_id + '&IDknjige=' + k.knj_id" style="background:lightgreen"><i class="fas fa-edit"></i>IZMENI</a></td>
           		<td><a v-bind:href="'/api/admin?com=d&id=' + k.kom_id + '&odakle=komentari'" style="background:red"><i class="fas fa-trash-alt"></i>OBRISI</a></td>
              </tr>
           	</table>

           	<h2>ZANROVI</h2>
           	 <a href="/dodaj?koga=zanrovi" class="code"><i class="fas fa-plus-circle"></i>DODAJ</a>
             <table>
           	   <tr>
           	    <th>ID</th>
           	    <th>NAZIV</th>
           	    <th colspan="2">ACTION</th>
           	   </tr>

           	<tr v-for="z in zanrovi">
           	   <td>{{ z.znr_id }}</td>
           	   <td>{{ z.znr_naziv }}</td>
           	   <td><a v-bind:href="'/dodaj?koga=zanrovi&edit=d&id=' + z.znr_id + '&naziv=' + z.znr_naziv " style="background:lightgreen"><i class="fas fa-edit"></i>IZMENI</a></td>
           	   <td><a v-bind:href="'/api/admin?com=d&id=' + z.znr_id + '&odakle=zanrovi'" style="background:red"><i class="fas fa-trash-alt">OBRISI</i></a></td>
           	</tr>
           	</table>

           	 <h2>IZDAVACI</h2>
           	  <a href="/dodaj?koga=izdavaci" class="code"><i class="fas fa-plus-circle"></i>DODAJ</a>
           	   <table>
           	    <tr>
           		 <th>ID</th>
           		 <th>NAZIV</th>
           		 <th colspan="2">ACTION</th>
           		</tr>

           	    <tr v-for="i in izdavaci">
           		  <td>{{ i.izd_id }}</td>
           		  <td>{{ i.izd_naziv }}</td>
           		  <td><a v-bind:href="'/dodaj?koga=izdavaci&edit=d&id=' + i.izd_id + '&naziv=' + i.izd_naziv " style="background:lightgreen;"><i class="fas fa-edit"></i>IZMENI</a></td>
           		  <td><a v-bind:href="'/api/admin?com=d&id=' + i.izd_id + '&odakle=izdavaci'" style="background:red"><i class="fas fa-trash-alt"></i>OBRISI</a></td>
           		</tr>
           	</table>

            <h2>AUTORI</h2>
            <a href="/dodaj?koga=autori" class="code"><i class="fas fa-plus-circle"></i>DODAJ</a>
               <table>
                 <tr>
                   <th>ID</th>
                   <th>NAZIV</th>
                   <th colspan="2">ACTION</th>
                 </tr>
                 <tr v-for="a in autori">
                    <td>{{ a.aut_id }}</td>
                    <td>{{ a.aut_naziv }}</td>
                    <td><a v-bind:href="'/dodaj?koga=autori&edit=d&id=' + a.aut_id + '&naziv=' + a.aut_naziv " style="background:lightgreen"><i class="fas fa-edit"></i>IZMENI</a></td>
                    <td><a v-bind:href="'/api/admin?com=d&id=' + a.aut_id + '&odakle=autori'" style="background:red"><i class="fas fa-trash-alt"></i>OBRISI</a></td>
                 </tr>
               </table>

            <h2>JEZICI</h2>
            <a href="/dodaj?koga=jezici" class="code"><i class="fas fa-plus-circle"></i>DODAJ</a>
                <table>
                  <tr>
                    <th>ID</th>
                    <th>NAZIV</th>
                    <th colspan="2">ACTION</th>
                  </tr>

                  <tr v-for="j in jezici">
                    <td>{{ j.jez_id }}</td>
                    <td>{{ j.jez_naziv }}</td>
                    <td><a v-bind:href="'/dodaj?koga=jezici&edit=d&id=' + j.jez_id + '&naziv=' + j.jez_naziv" style="background:lightgreen"><i class="fas fa-edit"></i>IZMENI</a></td>
                    <td><a v-bind:href="'/api/admin?com=d&id='+ j.jez_id + '&odakle=jezici'" style="background:red;width:40px"><i class="fas fa-trash-alt"></i>OBRISI</a></td>
                  </tr>
                </table>

             </div>
          </section>
      </div>
    `
 }
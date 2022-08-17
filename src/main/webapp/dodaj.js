
    const Dodaj={
       data(){
          const admin=[];
          const zanrovi="";
          const autori="";
          const jezici="";
          const knjige="";
          const korisnici="";
          const komentari="";
          const izdavaci="";
          const edit="";
          const dodaj="";
          const nesto = false;
          return{admin,edit,dodaj,nesto}
       },

       methods:{

         dodajF() {
           console.log(this.admin);

          if(this.admin == "korisnici"){
            get('/api/admin?com=i&odakle=' + this.admin + '&user=' + this.user + '&password=' + this.password + '&ime=' + this.ime + '&prezime=' + this.prezime + '&email=' + this.email + '&slika=' + this.slika + '&tip=' + this.tip),{},(data)=>{
          }
            router.push("/admin");
          }
          if(this.admin == "autori"){
            get('/api/admin?com=i&odakle=' + this.admin + '&naziv=' + this.naziv),{},(data)=>{
          }
            router.push("/admin");
          }
          if(this.admin == "zanrovi"){
            get('/api/admin?com=i&odakle=' + this.admin + '&naziv=' + this.naziv),{},(data)=>{
          }
             router.push("/admin");
          }
          if(this.admin == "komentari"){
            get('/api/admin?com=i&odakle=' + this.admin + '&komentar=' + this.komentar + '&ocena=' + this.ocena + '&IDkorisnika=' + this.IDkorisnika + '&IDknjige=' + this.IDknjige),{},(data)=>{
          }
            router.push("/admin");
          }
          if(this.admin == "izdavaci"){
            get('/api/admin?com=i&odakle=' + this.admin + '&naziv=' + this.naziv),{},(data)=>{
          }
            router.push("/admin");
          }
          if(this.admin == "knjige"){
            get('/api/admin?com=i&odakle=' + this.admin + '&naziv=' + this.naziv + '&isbn=' + this.isbn + '&opis=' + this.opis + '&cena=' + this.cena + '&slika=' + this.slika + '&IDizdavac=' + this.IDizdavac + '&IDjezik=' + this.IDjezik + '&zanr=' + this.zanr + '&autor=' + this.autor),{},(data)=>{
          }
           // router.push("/admin");
          }
          if(this.admin == "jezici"){
            get('/api/admin?com=i&odakle=' + this.admin + '&naziv=' + this.naziv),{},(data)=>{
          }
            router.push("/admin");
         }

     },

        editF() {
          console.log(this.admin);

          if(this.admin == "korisnici"){
            get('/api/admin?com=u&odakle=' + this.admin + '&user=' + this.user + '&password=' + this.password + '&ime=' + this.ime + '&prezime=' + this.prezime + '&email=' + this.email + '&slika=' + this.slika + '&tip=' + this.tip + '&id=' + this.id),{},(data)=>{
          }
            router.push("/admin");
          }
          if(this.admin == "autori"){
            get('/api/admin?com=u&odakle=' + this.admin + '&naziv=' + this.naziv + '&id=' + this.id),{},(data)=>{
          }
            router.push("/admin");
          }
          if(this.admin == "zanrovi"){
            get('/api/admin?com=u&odakle=' + this.admin + '&naziv=' + this.naziv + '&id=' + this.id),{},(data)=>{
          }
            router.push("/admin");
          }
           if(this.admin == "komentari"){
             get('/api/admin?com=u&odakle=' + this.admin + '&komentar=' + this.komentar + '&ocena=' + this.ocena + '&IDkorisnika=' + this.IDkorisnika + '&IDknjige=' + this.IDknjige + '&id=' + this.id),{},(data)=>{
           }
             router.push("/admin");
           }
           if(this.admin == "izdavaci"){
             get('/api/admin?com=u&odakle=' + this.admin + '&naziv=' + this.naziv + '&id=' + this.id),{},(data)=>{
             }
               router.push("/admin");
           }
           if(this.admin == "knjige"){
             get('/api/admin?com=u&odakle=' + this.admin + '&naziv=' + this.naziv + '&isbn=' + this.isbn + '&opis=' + this.opis + '&cena=' + this.cena + '&slika=' + this.slika + '&id=' + this.id + '&IDizdavac=' + this.IDizdavac + '&IDjezik=' + this.IDjezik + '&zanr=' + this.zanr + '&autor=' + this.autor),{},(data)=>{
            }
            router.push("/admin");
           }
           if(this.admin == "jezici"){
             get('/api/admin?com=u&odakle=' + this.admin + '&naziv=' + this.naziv + '&id=' + this.id),{},(data)=>{
             console.log(data);
           }
              router.push("/admin");
          }
         }
       },

     mounted(){
         this.id="";
         this.naziv="";
         this.opis="";
         this.cena="";
         this.isbn="";
         this.slika="";
         this.komentar="";
         this.ocena="";
         this.IDkorisnika="";
         this.IDknjige="";
         this.user="";
         this.password="";
         this.ime="";
         this.prezime="";
         this.tip="";
         this.email="";
         this.zanr="";
         this.autor="";
         this.IDjezik="";
         this.file="";
         this.IDizdavac="";

     get('/api/admin',{},(data)=>{
         if(data.res == "ok"){
            this.zanrovi=data.zanrovi;
            this.izdavaci=data.izdavaci;
            this.jezici=data.jezici;
            this.autori=data.autori;
            this.nesto = true;
               console.log(data);
         }else if(data.res == "err"){
             router.push("/login");
         }else{
           this.pocetna=data.pocetna;
        }
     });

        this.admin=this.$route.query.koga;
        this.edit=this.$route.query.edit;

           if (this.edit == "d") {

              if (this.admin == "autori") {
                 this.id=this.$route.query.id;
                 this.naziv=this.$route.query.naziv;
           }

              if(this.admin == "zanrovi"){
                  this.id=this.$route.query.id;
                  this.naziv=this.$route.query.naziv;
           }

              if(this.admin == "jezici"){
                  this.id=this.$route.query.id;
                  this.naziv=this.$route.query.naziv;
           }

              if(this.admin == "izdavaci"){
                  this.id=this.$route.query.id;
                  this.naziv=this.$route.query.naziv;
           }
              if(this.admin == "knjige"){
                  this.id=this.$route.query.id;
                  this.naziv=this.$route.query.naziv;
                  this.opis=this.$route.query.opis;
                  this.isbn=this.$route.query.isbn;
                  this.cena=this.$route.query.cena;
                  this.slika=this.$route.query.slika;
                  this.zanr=this.$route.query.zanr;
                  this.autor=this.$route.query.autor;
                  this.IDjezik=this.$route.query.IDjezik;
                  this.IDizdavac=this.$route.query.IDizdavac;
            }

                if(this.admin == "korisnici"){
                   this.id=this.$route.query.id;
                   this.user=this.$route.query.user;
                   this.password=this.$route.query.password;
                   this.ime=this.$route.query.ime;
                   this.prezime=this.$route.query.prezime;
                   this.email=this.$route.query.email;
                   this.slika=this.$route.query.slika;
                   this.tip=this.$route.query.tip;
            }

                if(this.admin == "komentari"){
                   this.id=this.$route.query.id;
                   this.komentar=this.$route.query.komentar;
                   this.ocena=this.$route.query.ocena;
                   this.IDkorisnika=this.$route.query.IDkorisnika;
                   this.IDknjige=this.$route.query.IDknjige;
                }
            }
         },

        template:`

         <div>
         <section v-if="!nesto"  class="admin" id="admin">
             <h1>Ucitavanje<i class="fas fa-spinner"></i></h1>
         </section>

         <section v-if="nesto">
            <div v-if="admin == 'korisnici'" class="contan">

             <fieldset>
             <legend>KORISNICI</legend>
             <label v-if="edit">ID</label>
             <input v-if="edit" type="text" v-model="id" class="input" placeholder="Unesi id korisnika" required><br>
             <label>KORISNICKO IME</label>
             <input type="text" v-model="user" class="input" placeholder="Unesi username" required><br>
             <label>PASSWORD</label>
             <input type="text" v-model="password" class="input" placeholder="Unesi password" required><br>
             <label>IME</label>
             <input type="text" v-model="ime" class="input" placeholder="Unesi ime" required><br>
             <label>PREZIME</label>
             <input type="text" v-model="prezime" class="input" placeholder="Unesi prezime" required><br>
             <label>EMAIL</label>
              <input type="text" v-model="email" class="input" placeholder="Unesi email" required><br>
             <label>IZABERI SLIKU</label>
             <input type="file" ref="file" @change="readFile()">
             <button @click="sendFile()">UPLOAD</button><br><br>
             <label>TIP KORISNIKA</label>
             <select v-model="tip">
                  <option value="">Tip korisnika</option>
                  <option value="1">Admin</option>
                  <option value="0">Korisnik</option>
            </select><br>

            <button v-if="!edit" type="button" class="but" @click="dodajF()">DODAJ</button>
            <button v-if="edit == 'd'" type="button" class="but" @click="editF()">IZMENI</button>
            </legend>
            </fieldset>
        </div>

          <div v-if="admin == 'knjige'" class="contan">
          <fieldset>
          <legend>KNJIGE</legend>
          <label v-if="edit">ID</label>
          <input v-if="edit" type="text" v-model="id" class="input" placeholder="Unesi id" required><br>
          <label>NAZIV KNJIGE</label>
          <input type="text" v-model="naziv" class="input" placeholder="Unesi naziv" required><br>
          <label>OPIS KNJIGE</label>
          <input type="text" v-model="opis" class="input" placeholder="Unesi opis" required><br>
          <label>ISBN</label>
          <input type="text" v-model="isbn" class="input" placeholder="Unesi isbn" required><br>
          <label>CENA</label>
          <input type="text" v-model="cena" class="input" placeholder="Unesi cenu" required><br>
           <label>ZANR</label>
           <select name='z' v-model="zanr" multiple>
              <option v-for="z in zanrovi" :value='z.znr_id'>{{ z.znr_naziv }}</option>
           </select><br>
           <label>AUTOR</label>
           <select name='a' v-model="autor" multiple>
              <option v-for="a in autori" :value='a.aut_id'>{{ a.aut_naziv }}</option>
           </select><br>
           <label>JEZIK</label>
           <select name='j' v-model="IDjezik">
              <option v-for="j in jezici" :value='j.jez_id'>{{ j.jez_naziv }}</option>
           </select><br>
           <label>IZDAVAC</label>
           <select name='i' v-model="IDizdavac">
              <option v-for="i in izdavaci" :value='i.izd_id'>{{ i.izd_naziv }}</option>
           </select><br>

          <button v-if="!edit" type="button" class="but" @click="dodajF()">DODAJ</button>
          <button v-if="edit == 'd'" type="button" class="but" @click="editF()">IZMENI</button>
          </fieldset>
          </div>

          <div v-if="admin == 'komentari'" class="contan">
          <fieldset>
          <legend>KOMENTARI</legend>
          <label v-if="edit">ID</label>
          <input type="text" v-if="edit" class="input" v-model="id" placeholder="Unesi id" required><br>
          <label>KOMENTAR</label>
          <input type="text" v-model="komentar" class="input" placeholder="Unesi komentar" required><br>
          <label>OCENA</label>
          <input type="text" v-model="ocena" class="input" placeholder="Unesi ocenu" required><br>
          <label>ID KORISNIKA</label>
          <input type="text" v-model="IDkorisnika" class="input" placeholder="Unesi id korisnika" required><br>
          <label>ID KNJIGE</label>
          <input type="text" v-model="IDknjige" class="input" placeholder="Unesi id knjige" required><br>

          <button v-if="!edit" type="button" class="but" @click="dodajF()">DODAJ</button>
          <button v-if="edit == 'd'" type="button" class="but" @click="editF()">IZMENI</button>
          </fieldset>
          </div>

          <div v-if="admin == 'zanrovi'" class="conta">
          <fieldset>
          <legend>ZANROVI</legend>
          <label v-if="edit">ID</label>
          <input v-if="edit" type="text" v-model="id" placeholder="Unesi id" class="foo" required><br>
          <label>NAZIV</label>
          <input type="text" v-model="naziv" placeholder="Unesi naziv" class="foo" required><br>

          <button v-if="!edit" type="button" class="but" @click="dodajF()">DODAJ</button>
          <button v-if="edit == 'd'" type="button" class="but" @click="editF()">IZMENI</button>
          </fieldset>
          </div>

          <div v-if="admin == 'autori'" class="conta">
          <fieldset>
          <legend>AUTORI</legend>
          <label v-if="edit">ID</label>
          <input v-if="edit" class="foo" type="text" v-model="id" placeholder="Unesi id" required><br>
          <label>NAZIV</label>
          <input type="text" class="foo" v-model="naziv" placeholder="Unesi naziv" required><br>

          <button v-if="!edit" type="button" class="but" @click="dodajF()">DODAJ</button>
          <button v-if="edit == 'd'" type="button" class="but" @click="editF()">IZMENI</button>
          </fieldset>
          </div>

          <div v-if="admin == 'izdavaci'" class="conta">
          <fieldset>
          <legend>IZDAVACI</legend>
          <label v-if="edit">ID</label>
          <input v-if="edit" class="foo" type="text" v-model="id" placeholder="Unesi id" required><br>
          <label>NAZIV</label>
          <input type="text" class="foo" v-model="naziv" placeholder="Unesi naziv" required><br>

          <button v-if="!edit" type="button" class="but" @click="dodajF()">DODAJ</button>
          <button v-if="edit == 'd'" type="button" class="but" @click="editF()">IZMENI</button>
          </fieldset>
          </div>

          <div v-if="admin == 'jezici'" class="conta">
          <fieldset>
          <legend>JEZICI</legend>
          <label  v-if="edit">ID</label>
          <input  v-if="edit" type="text" v-model="id" placeholder="Unesi id" class="foo" required><br>
          <label>NAZIV</label>
          <input type="text" v-model="naziv" placeholder="Unesi naziv" class="foo" required><br>

          <button v-if="!edit" type="button" class="but" @click="dodajF()">DODAJ</button>
          <button v-if="edit == 'd'" type="button" class="but" @click="editF()">IZMENI</button>
          </fieldset>
          </div>
          </section>
</div>
       `
}


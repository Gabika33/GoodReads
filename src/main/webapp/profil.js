
   const Profil = {
      data(){
        const login={};
        const logged=true;
        const admin=false;
        const username=null;
        const password=null;
        const edit="";
        let new_file=null;

          return{logged,admin,username,password,new_file,edit}
      },
      methods:{


         sendFile() {
            let formData=new FormData();
            formData.append('slika',this.new_file);
            formData.append('user',this.user);
            formData.append('password',this.password);
            formData.append('ime',this.ime);
            formData.append('prezime',this.prezime);
            formData.append('email',this.email);


            axios.post('/api/profil',formData,{
                headers: {
                  'Content-Type': 'multipart/form-data'//binarni sadrzaj;
                }
             }).then(function() {
            window.location.reload();
               // console.log("UPLOAD");
             }).catch(function(e) {
               // console.log("ERROR");
               alert("Greska");
             })
         },
             prepareFile() {
              this.new_file=this.$refs.fileInput.files[0];

            }
         },

      mounted(){

            get("/api/profil",{},(data)=>{
                       if (data.res == "ok"){
                                    this.id=data.id;
                                     this.user=data.username;
                                     this.password=data.pass;
                                     this.ime=data.ime;
                                     this.prezime=data.prezime;
                                      this.email=data.email;
                       }else{
                       alert("Greska. ");
                       }
            });
       },

      template:`
       <div style="background:radial-gradient(mediumblue,white);">

       <div class="profilbox">
           <h2>KORISNICKI PROFIL</h2>
           <img :src="'/api/loginslika?id='+id"><br>
           <span>Korisnicko ime</span>
           <input type="text" v-model="user" class="box" placeholder="Unesite korisnicko ime" required>
           <span>Lozinka</span>
           <input type="text" v-model="password" class="box" placeholder="Unesite lozinku" required>
           <span>Ime</span>
           <input type="text" v-model="ime" class="box" placeholder="Unesite ime" required>
           <span>Prezime</span>
           <input type="text" v-model="prezime" class="box" placeholder="Unesite prezime" required>
           <span>Email</span>
           <input type="text" v-model="email" class="box" placeholder="Unesite email" required>
           <span>Izaberi sliku</span>
           <input type="file" class="file" ref="fileInput" @change="prepareFile()">
           <button class="but" @click="sendFile()">UPLOAD I SACUVAJ</button>
</div>
       </div>
  `
}
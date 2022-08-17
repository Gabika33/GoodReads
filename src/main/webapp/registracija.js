
  const Registracija= {
     data(){
        const username="";
        const password="";
        const repeatpassword="";
        const ime="";
        const prezime="";
        const email="";


       return{username,password,repeatpassword,ime,prezime,email}
     },

     methods:{
       registration(){
         get('/api/registracija',{username:this.username,password:this.password,repeatpassword:this.repeatpassword,ime:this.ime,prezime:this.prezime,email:this.email},(data)=>{
            if(data.res=="ok"){
              router.push("/login");
            }else{
             alert(data.resData);

              }
         })
       }
     },
     mounted(){

     },
     template:`
       <div class="registration">
          <h2>Registracija</h2>
             <div class="card">
            <div class="carde">
            <span>Ime</span>
            <input type="text" v-model="ime" class="reg" placeholder="Unesi ime" required>
            </div>
            <div class="carde">
            <span>Prezime</span>
            <input type="text" v-model="prezime" class="reg" placeholder="Unesi prezime" required>
            </div>
            <div class="carde">
            <span>Email</span>
            <input type="text" v-model="email" class="reg" placeholder="Unesi email" required>
            </div>
            <div class="carde">
       	    <span>Korisnicko ime</span>
       	    <input type="text" v-model="username" class="reg" placeholder="Unesi korisnicko ime" required>
            </div>
            <div class="carde">
       	    <span>Lozinka</span>
       	    <input type="password" v-model="password" class="reg" placeholder="Unesi lozinku" required>
            </div>
            <div class="carde">
            <span>Ponovi lozinku</span>
       	    <input type="repeatpassword" v-model="repeatpassword" class="reg" placeholder="Unesi lozinku" required>
           </div>
            <button class="bttn" type="button" @click="registration()">Registruj se</button>

         </div>
     </div>
</div>
     `
}

    const Login={
       data() {
         const username="";
         const password="";
       return{username,password}
    },

    methods:{
      test(){
        if(app.logged==true){
           router.push("/home");
         }
      },
      testLogin(){
        get('/api/login',{username:this.username,password:this.password},(data)=>{
            if(data.res="ok"){
              app.logged==true;

              get("/api/home",{},(data)=>{
                        if(data.res == "ok"){
                           alert("Dobrodosli: " + data.resData['ime'] + " " + data.resData['prezime']);
                      }
                    });
                  window.location.href="http://localhost:8080/";

         }else{
           alert(data.resData);
         }
        })
      }
    },

    mounted(){
       this.test();
    },

    template:`
    <div style="background:radial-gradient(mediumblue,white);height:100%;">
     <div class="loginbox">
        <img src="img/login.png"  class="avatar"/>
           <h2>Login</h2>
              <span>Korisnicko ime</span>
              <input type="text" v-model="username" class="boxes" placeholder="Unesite korisnicko ime" required>
              <span>Lozinka</span>
              <input type="password" v-model="password" class="boxes" placeholder="Unesite lozinku" required>
              <button class="button" type="button" @click="testLogin()">Login</button>
              <p>Nemate nalog ? <router-link class="right" :to="'/registracija'">Registruj se</router-link></p>
     </div>
 </div>



 `
}
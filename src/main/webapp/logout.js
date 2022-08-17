
    const Logout= {
      data(){
         return{
      }
   },

      methods:{
        getLogout(){
           get("/api/logout",{},(data)=>{
             app.logged=false;
               router.push("/login");
         });
      }
   },

   mounted(){
       this.getLogout();
   },

   template:`

    <div>
        </div>
   `
}
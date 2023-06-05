<template>
        <v-app-bar
          color="teal-darken-2"
        >
          
  
          <template v-slot:prepend>
            <v-app-bar-nav-icon @click.stop="drawer = !drawer">
              
            </v-app-bar-nav-icon>
            
          </template>
  
          <v-app-bar-title>{{ title }}</v-app-bar-title>
  
          <v-spacer></v-spacer>

          <div v-if="title=='Competition pages'">
            <v-btn density="comfortable" style="background-color: teal;">
              <router-link to="/competitions" class="router">
                Back to competitions
              </router-link>
            </v-btn>
          </div>

          <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
  
          <v-btn icon>
            <router-link to="/" style="color:white">
              <v-icon>mdi-home
              </v-icon>
            </router-link>
          </v-btn>

          
  
          <v-btn icon>
            <v-icon>mdi-dots-vertical</v-icon>
          </v-btn>
        </v-app-bar>

          <v-navigation-drawer
                  expand-on-hover
                  rail
                  temporary
                  v-model="drawer"
                  style="max-width: 200px; min-width: 120px; width: 20vw; height: 37vh; border-bottom-right-radius: 2%;"
                >
                  
                  <v-divider></v-divider>

                  <v-list  nav>
                    <v-list-item to="/" title="Home Page" value="myfiles" router></v-list-item>
                    <v-list-item to="/competitions" title="Competitions" value="myfiles" router></v-list-item>
                    <v-list-item to="/qualified-athletes" title="Qualified Athletes" value="myfiles" router></v-list-item>
                    <v-list-item to="/swimmers" title="Swimmers" value="Swimmers" router></v-list-item>
                    <v-list-item to="/pools" title="Pools" value="Pools" router></v-list-item>
                    <v-list-item to="/world-records" title="World Records" value="myfiles" router></v-list-item>
                  </v-list>
           </v-navigation-drawer>
</template>
<script>
export default {
  name: 'Header',
  props: {
    title: String,
  },
  data() {
    return {
      drawer: false,
      group: null,
    }
  },
  watch: {
      group() {
        this.drawer = false
      },
    },
}
</script>

<style scoped>

.router {
  text-decoration: none;
  color:white;
}
</style>
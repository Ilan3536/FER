<template>
        <v-app-bar
          color="teal-darken-2"
        >
          
  
          <template v-slot:prepend>
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
          </template>
  
          <v-app-bar-title>{{ title }}</v-app-bar-title>
  
          <v-spacer></v-spacer>
  
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
</template>
<script>
export default {
  name: 'Header',
  props: {
    title: String,
  },
}
</script>
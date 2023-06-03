<template>
      
        <v-layout class="page-container">
          <Header :title="title"/>
          <v-main class="content-wrap">
            <slot />
          </v-main>
          <Footer class="footer"/>
        </v-layout>

</template>

<script>
import Header from './Header.vue'
import Footer from './Footer.vue'

export default {
  name: 'Layout',
  components: {
    Header,
    Footer
  },
  props: {
    title: String,
  },
}
</script>

<style>
.page-container {
  display: flex;
  flex-direction: column;
}

.footer {
  position: fixed;
  bottom: 0;
  left:0;
  margin-top: 10vh;
  width: 100%;
}
</style>
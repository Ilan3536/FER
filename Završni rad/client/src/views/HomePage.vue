<template>
    <Layout title="Home page">

        <v-carousel
            height="70vh"
            hide-delimiter-background
            show-arrows="hover"
        >
            <v-carousel-item
                v-for="(item, i) in carouselItems"
                :key="i"
                cover
            >
                <v-parallax
                    :src="getImagePath(item.src)"
                    height="70vh"
                    class="position-relative"
                    >
                        <div class="d-flex flex-column fill-height justify-center align-center text-white">
                            <v-col cols="auto">
                                <v-btn size="large">
                                    <router-link :to="item.route" class="router-link" >{{ item.caption }}</router-link>
                                </v-btn>
                            </v-col>
                            <div class="overlay">
                                <h3 class="subheading">{{ item.description }}</h3>
                            </div>
                        </div>
                </v-parallax>
            </v-carousel-item>
        </v-carousel>
    </Layout>
</template>
  
<script>
import Layout from '@/components/Layout/Layout.vue';
export default {
    name: "HomePage",
    components: {
        Layout,
    },
    data() {
        return {
            carouselItems: [
                {   src: 'start-london-roar.png',
                    caption: 'Welcome to the World of Swimming', 
                    description: 'Dive into the captivating realm of swimming, where records are shattered and champions are born.',
                    route: ''
                },
                {   src: 'natjecanja-image.jpg',
                    caption: 'Competitions', 
                    description: 'Immerse yourself in the world of swimming competitions and explore results from various events.',
                    route: '/competitions'
                }, 
                {   src: 'pool.jpg',
                    caption: 'Qualified Athletes', 
                    description: 'Explore the accomplishments of swimmers who have met the qualification limits in their respective events.',
                    route: '/qualified-athletes' 
                },
                {   src: 'katie.jpeg', 
                    caption: 'Swimmers', 
                    description: 'Discover Swim Times by Exceptional Athletes.',
                    route: '/swimmers'
                }, 
                {   src: 'swim-pool.jpg',
                    caption: 'Pools', 
                    description: 'Discover the astonishing feats of swimmers who have achieved world records.',
                    route: '/pools'
                }, 
                {   src: 'dressel.jpg', 
                    caption: 'World Records', 
                    description: 'Dive into a collection of stunning swimming pool images, showcasing the beauty and serenity of aquatic environments.',
                    route: '/world-records'
                }, 
            ],
        };
   },
    methods: {
        getImagePath(image) {
            return require('@/assets/homePageImages/' + image);
        }
   }
}
</script>
<style>
.position-relative {
    position: relative;
    
}
.router-link {
    text-decoration: none;
    color: black;
}
.overlay {
  position: absolute;
  top: 55%;
  left: 50;
  width: 450px;
  height: 150px;
  background-color: rgba(0,121, 107, 0.8);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.subheading {
  color: white;
  text-align: center;
  padding: 10px;
}



</style>
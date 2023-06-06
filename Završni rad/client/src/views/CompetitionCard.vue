


<template>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
    <v-card
        class="mx-auto"
        max-width="344"
    >
            <v-img
                :src="getImagePath(competition.bazen.grad)"
                height="200px"
                cover
            ></v-img>

            <v-card-title class="multi-row-text">
                {{ competition.nazivnatjecanje }}
            </v-card-title>

            <v-card-subtitle>
                {{ competition.bazen.drzava.nazivdrzava }}
            </v-card-subtitle>

            <v-card-actions>
                <v-btn
                    color="orange-lighten-2"
                    variant="text"
                    @click="this.$router.push(`/competitions/${competition.idnatjecanje}`)"
                    class="material-symbols-outlined"
                >
                description
                </v-btn>
                <v-btn
                    color="teal-darken-4"
                    variant="text"
                    @click="this.$router.push(`/competitions/chart/${competition.idnatjecanje}`)"
                    class="material-symbols-outlined"
                >
                insert_chart
                </v-btn>

            <v-spacer></v-spacer>

            <v-btn
                :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
                @click="show = !show"
            ></v-btn>
            </v-card-actions>

            <v-expand-transition>
            <div v-show="show">
                <v-divider></v-divider>

                <v-card-text>
                    {{ 'Pool: ' + competition.bazen.nazivbazen }} <br>
                    {{ 'City: ' + competition.bazen.grad }} <br>
                    {{ 'Date: ' + competition.datumod + ' - ' + competition.datumdo }}
                </v-card-text>
            </div>
            </v-expand-transition>
        </v-card>
</template>
<script>
export default {
    name: 'CompetitionCard',
    props: {
        competition: Object,
    },
    data() {
        return {
            show: false,
        }
    },
    methods: {
        getImagePath(image) {
            return require('@/assets/cities/' + image + '.jpg');
        }
   }
    
}
</script>
<style scoped>
.multi-row-text {
  white-space: pre-wrap; 
  word-break: break-word;
  min-height: 4em;
}
    
</style>
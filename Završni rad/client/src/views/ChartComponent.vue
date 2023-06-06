<template>
  <Layout title="Number of Swimmers by Event">
    <BarChart :chartData="realData" :options="options" style="height: 70vh;" />
  </Layout>
</template>

<script>
import { defineComponent } from 'vue';
import { BarChart } from 'vue-chart-3';
import { Chart, registerables } from "chart.js";
import Layout from '@/components/Layout/Layout.vue'
import { mapActions, mapState } from 'vuex';


Chart.register(...registerables);

export default defineComponent({
  name: 'Home',
  components: {
    Layout,
    BarChart
  },
  data() {
    return {
      testData: {
        labels: ['Paris', 'Nîmes', 'Toulon', 'Perpignan', 'Autre'],
          datasets: [
            {
              data: [30, 40, 60, 70, 5],
              backgroundColor: ['#77CEFF', '#0079AF', '#123E6B', '#97B0C4', '#A5C8ED'],
            },
          ],
      },
      realData : {
        labels: [],
        datasets : [
          {
            data: [],
            backgroundColor: ['#ADD8E6', '#DB7093'],
          }
        ]
      },
      options : {
        plugins : {
          legend: {
            labels: {
              generateLabels: () => {
                const legendItems = [];

                legendItems.push({
                  text: 'Men',
                  fillStyle: '#ADD8E6',
                });

                legendItems.push({
                  text: 'Women',
                  fillStyle: '#FF69B4',
                });

                return legendItems;
              },
            }
          },
        }
      }
    };
  },
  created() {
    this.fetchRezultatiCount(this.$route.params.id)
  },
  computed: {
    ...mapState('rezultati', ['resultsCount']),
   
  },
  watch :{
    resultsCount :{
      handler(){
        this.mapData()
      }
    }
  },
  methods: {
    ...mapActions('rezultati', ['fetchRezultatiCount']),
    mapData(){
      this.realData.labels = this.resultsCount.map(item => 
       item[1] + " " + item[2]
      )
      this.realData.datasets[0].data = this.resultsCount.map(item => 
        item[3]
      )
      console.log(this.testData)
      console.log(this.realData)
    }
  }


});
</script>

<template>
    <div>
        <div id="main" ref="main"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment'
import {getTotalVehicleProductionData} from '@/api/financial/data'

export default {
    data(){
        return{
            financialId: '',
            data:[]
        }
    },
    created(){
        this.financialId = this.$route.query.id
    },
    async mounted(){ 
        try{
            const res = await getTotalVehicleProductionData(this.financialId)
            console.log(res, 'res---')
            this.data = res.rows
        } catch(error) {
            console.log(error)
        }
        
        var myChart = echarts.init(document.getElementById('main'));
        let option = {
            title: {
              text: '整车产量'
            },
            tooltip: {},
            xAxis: {
                data: this.data.map(item => moment(item.Year_And_Month).format('YY-MM')),
            },
            yAxis: {},
            series: [
             {
                name: '产量',
                type: 'bar',
                data: this.data.map(item => item.Total_Vehicle_Production),
                label: {
                    show: true, 
                    position: 'top' 
                }
            },
            ],
        };
        myChart.setOption(option);
    }

}


</script>

<style scoped>
#main {
  width: 1000px;
  height: 600px;
  margin: 40px auto;
}
</style>
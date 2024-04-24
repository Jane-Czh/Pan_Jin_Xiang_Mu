<template>
    <div>
        <div id="main" ref="main"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment'
import {getAddedValueMonthlyData} from '@/api/financial/data'

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
            const res = await getAddedValueMonthlyData(this.financialId)
            console.log(res, 'res---')
            this.data = res.rows
        } catch(error) {
            console.log(error)
        }
        
        var myChart = echarts.init(document.getElementById('main'));
        let option = {
            title: {
              text: '当月经济增加值'
            },
            tooltip: {},
            xAxis: {
                data: this.data.map(item => moment(item.Year_And_Month).format('YY-MM')),
            },
            yAxis: {},
            series: [
             {
                name: '金额',
                type: 'bar',
                data: this.data.map(item => item.Added_Value_Monthly),
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
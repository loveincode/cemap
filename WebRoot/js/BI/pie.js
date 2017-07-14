var myPieChart = echarts.init(document.getElementById('people-chart'),'macarons');
    option = {
        title : {
            text: '毕业生人数比例',
            subtext: '2017届毕业生总数 3350人 专科：700 本科：2350 研究生：300',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:['专科','本科','研究生']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'人数比例',
                type:'pie',
                radius : [20, 110],
                center : ['25%', '50%'],
                roseType : 'radius',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                lableLine: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                data:[
                    {value:700, name:'专科'},
                    {value:2350, name:'本科'},
                    {value:300, name:'研究生'},
                ]
            },
            {
                name:'面积模式',
                type:'pie',
                radius : [30, 110],
                center : ['75%', '50%'],
                roseType : 'area',
                data:[
                    {value:700, name:'专科'},
                    {value:2350, name:'本科'},
                    {value:300, name:'研究生'},
                ]
            }
        ]
    };
    myPieChart.setOption(option);   
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
	<title>INDEX</title>
   <%@include file="/WEB-INF/views/common/commoncss.jsp" %>
</head>
<style>
.chart{ min-height: 350px; }
.hy-chart{ min-height: 550px; }
.zz-chart{ min-height: 1550px; }
.companynature-chart{ min-height: 450px; }
.qx-chart{min-height: 550px;}
</style>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
    	
    	
    	<!-- ************************* Search Form Start  ************************* -->
    	<div class="row">
    		
    		<div class="col-md-3 col-md-offset-3">
				<div class="form-group">
						<label class="col-md-5 control-label" style="padding-left:5px;padding-top:6px;">基数届数：</label>
						<div class="col-md-7">
							<select name="sgraduationSession" id="sgraduationSession" class="form-control">
								<option value="-1">请选择</option>
								<c:forEach items="${sessions}" var="session">
		        					<option value="${session}">${session}</option>
		        				</c:forEach> 
	      					</select>
						</div>
					</div>
			</div>
			
			<div class="col-md-3" style="padding-left:45px;">
            <button type="button"  id="query-btn" class="btn btn-success" ><i class="fa fa-search"></i> 查看</button>
          	 </div>
			<br/>
			<br/>
		    
       </div>
       <!-- *************************   Search Form End  ************************* -->


	   <!-- *************************     Table Start    ************************* -->
       <br/>
	   <br/>
       <div class="row">
       		<!-- 毕业人数 -->
       		<div class="col-md-4 col-md-offset-1 chart" id="allpeople-chart"></div>
       		<!-- 就业率 -->
       		<div class="col-md-6 chart" id="employ-chart"></div>
       </div>
       <br/>
	   <br/>
       <div class="row">
       		<!-- 薪资分布 -->
       		<div class="col-md-10 col-md-offset-1 chart" id="xz-chart"></div>
       </div>
       <br/>
	   <br/>
       <div class="row">
       		<!-- 毕业去向分布 -->
       		<div class="col-md-10 col-md-offset-1 qx-chart" id="qx-chart"></div>
       </div>
       
       
       
       <!-- *************************     Table End    ************************* -->
   </div>
   
    <!-- 全局js -->
    <%@include file="/WEB-INF/views/common/commonjs.jsp" %>
    
    <script src="${ctxStatic}/user/js/plugins/footable/footable.all.min.js"></script>
	<script src="${ctxStatic}/js/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script src="${ctxStatic}/user/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="${ctxStatic}/user/js/demo/layer-demo.min.js"></script>
    <!-- 自定义js -->
    <script src="${ctxStatic}/user/js/content.min.js?v=1.0.0"></script>
    <script type="text/javascript">
    //综合搜索
    $("#query-btn").on("click",function(){
		sgraduationSession = $("#sgraduationSession").val();
    	Bionload(sgraduationSession);
    });
    Bionload(2017);
    function Bionload(session){
    	if(session!=-1){
	    	//peopledata
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bicompareschool/peopledata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        },
		        success: function (data) {
		        		array = data.data;
		        		var name = new Array();
		        		var number =  new Array();
		        		for(var i = 0;i<array.length;i++){
		        			name[i] = array[i]["method"];
		        			number[i] = array[i]["counts"];
		        		}
		        		console.log("毕业生人数");
		        		console.log(name);
		        		console.log(number);
		        		people(name,number)
		        }
		    })
		    //employratedata
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bicompareschool/employratedata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        },
		        success: function (data) {
		        	if(data.code==500){
		        		console.log("error")
		        	}
		        	else{
		        		array = data.data;
		        		var name = new Array();
		        		var number =  new Array();
		        		for(var i = 0;i<array.length;i++){
		        			name[i] = array[i]["method"];
		        			number[i] = array[i]["counts"];
		        		}
		        		console.log("就业率");
		        		console.log(name);
		        		console.log(number);
		        		employrete(name,number)
		        	}
		        }
		    })
	    	
	    	//paydata
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bicompareschool/paydata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        },
		        success: function (data) {
		        		//alert(data.message);
		        		var sessiondata = [session-4,session-3,session-2,session-1,session];
		        		
		        		biCountPay5 = data.data[0][session];
		        		var paydata5 = [biCountPay5.less4,biCountPay5.more4less6,biCountPay5.more6less8,
		        		biCountPay5.more8less10,biCountPay5.more10less12,biCountPay5.more12less15,
		        		biCountPay5.more15less20,biCountPay5.more20less30,biCountPay5.more30];
		        		
		        		biCountPay4 = data.data[0][session-1];
		        		var paydata4 = [biCountPay4.less4,biCountPay4.more4less6,biCountPay4.more6less8,
		        		biCountPay4.more8less10,biCountPay4.more10less12,biCountPay4.more12less15,
		        		biCountPay4.more15less20,biCountPay4.more20less30,biCountPay4.more30];
		        		
		        		biCountPay3 = data.data[0][session-2];
		        		var paydata3 = [biCountPay3.less4,biCountPay3.more4less6,biCountPay3.more6less8,
		        		biCountPay3.more8less10,biCountPay3.more10less12,biCountPay3.more12less15,
		        		biCountPay3.more15less20,biCountPay3.more20less30,biCountPay3.more30];
		        		
		        		
		        		biCountPay2 = data.data[0][session-3];
		        		var paydata2 = [biCountPay2.less4,biCountPay2.more4less6,biCountPay2.more6less8,
		        		biCountPay2.more8less10,biCountPay2.more10less12,biCountPay2.more12less15,
		        		biCountPay2.more15less20,biCountPay2.more20less30,biCountPay2.more30];
		        		
		        		
		        		biCountPay1 = data.data[0][session-4];
		        		var paydata1 = [biCountPay1.less4,biCountPay1.more4less6,biCountPay1.more6less8,
		        		biCountPay1.more8less10,biCountPay1.more10less12,biCountPay1.more12less15,
		        		biCountPay1.more15less20,biCountPay1.more20less30,biCountPay1.more30];
		        		
		        		
		        		console.log("年薪分布");
		        		console.log(sessiondata);
		        		console.log(paydata1);
		        		console.log(paydata2);
		        		console.log(paydata3);
		        		console.log(paydata4);
		        		console.log(paydata5);
		        		
		        		paychart(paydata5);
		            	// window.location.reload();
		        }
		    })
		    //methoddata
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bicompareschool/methoddata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        },
		        success: function (data) {
		        	if(data.code==500){
		        		console.log("error")
		        	}
		        	else{
		        		
		        		array5 = data.data[0][session];
		        		array4 = data.data[0][session-1];
		        		array3 = data.data[0][session-2];
		        		array2 = data.data[0][session-3];
		        		array1 = data.data[0][session-4]; 
		        		
		        		var name = ['签三方','签合同','升学','出国','自主创业','参军'];
		        		var sessiondata = [session-4,session-3,session-2,session-1,session];
		        		var number1 = [0,0,0,0,0];
		        		var number2 = [0,0,0,0,0];
		        		var number3 = [0,0,0,0,0];
		        		var number4 = [0,0,0,0,0];
		        		var number5 = [0,0,0,0,0];
		        		var number6 = [0,0,0,0,0];
		        		
		        		for(var i = 0;i<array5.length;i++){
		        			if(array5[i]["method"]=="签就业协议书"){
		        				number1[4] =  array5[i]["counts"];
		        			}
		        			else if(array5[i]["method"]=="签合同"){
		        				number2[4] =  array5[i]["counts"];
		        			}
		        			else if(array5[i]["method"]=="升学"){
		        				number3[4] =  array5[i]["counts"];
		        			}
		        			else if(array5[i]["method"]=="出国"){
		        				number4[4] =  array5[i]["counts"];
		        			}
		        			else if(array5[i]["method"]=="自主创业"){
		        				number5[4] =  array5[i]["counts"];
		        			}
		        			else if(array5[i]["method"]=="参军"){
		        				number6[4] =  array5[i]["counts"];
		        			}
		        		}
		        		
		        		for(var i = 0;i<array4.length;i++){
		        			if(array4[i]["method"]=="签就业协议书"){
		        				number1[3] =  array4[i]["counts"];
		        			}
		        			else if(array4[i]["method"]=="签合同"){
		        				number2[3] =  array4[i]["counts"];
		        			}
		        			else if(array4[i]["method"]=="升学"){
		        				number3[3] =  array4[i]["counts"];
		        			}
		        			else if(array4[i]["method"]=="出国"){
		        				number4[3] =  array4[i]["counts"];
		        			}
		        			else if(array4[i]["method"]=="自主创业"){
		        				number5[3] =  array4[i]["counts"];
		        			}
		        			else if(array4[i]["method"]=="参军"){
		        				number6[3] =  array4[i]["counts"];
		        			}
		        		}
		        		
		        		for(var i = 0;i<array3.length;i++){
		        			if(array3[i]["method"]=="签就业协议书"){
		        				number1[2] =  array3[i]["counts"];
		        			}
		        			else if(array3[i]["method"]=="签合同"){
		        				number2[2] =  array3[i]["counts"];
		        			}
		        			else if(array3[i]["method"]=="升学"){
		        				number3[2] =  array3[i]["counts"];
		        			}
		        			else if(array3[i]["method"]=="出国"){
		        				number4[2] =  array3[i]["counts"];
		        			}
		        			else if(array3[i]["method"]=="自主创业"){
		        				number5[2] =  array3[i]["counts"];
		        			}
		        			else if(array3[i]["method"]=="参军"){
		        				number6[2] =  array3[i]["counts"];
		        			}
		        		}
		        		
		        		for(var i = 0;i<array2.length;i++){
		        			if(array2[i]["method"]=="签就业协议书"){
		        				number1[1] =  array2[i]["counts"];
		        			}
		        			else if(array2[i]["method"]=="签合同"){
		        				number2[1] =  array2[i]["counts"];
		        			}
		        			else if(array2[i]["method"]=="升学"){
		        				number3[1] =  array2[i]["counts"];
		        			}
		        			else if(array2[i]["method"]=="出国"){
		        				number4[1] =  array2[i]["counts"];
		        			}
		        			else if(array2[i]["method"]=="自主创业"){
		        				number5[1] =  array2[i]["counts"];
		        			}
		        			else if(array2[i]["method"]=="参军"){
		        				number6[1] =  array2[i]["counts"];
		        			}
		        		}
		        		
		        		for(var i = 0;i<array1.length;i++){
		        			if(array1[i]["method"]=="签就业协议书"){
		        				number1[0] =  array1[i]["counts"];
		        			}
		        			else if(array1[i]["method"]=="签合同"){
		        				number2[0] =  array1[i]["counts"];
		        			}
		        			else if(array1[i]["method"]=="升学"){
		        				number3[0] =  array1[i]["counts"];
		        			}
		        			else if(array1[i]["method"]=="出国"){
		        				number4[0] =  array1[i]["counts"];
		        			}
		        			else if(array1[i]["method"]=="自主创业"){
		        				number5[0] =  array1[i]["counts"];
		        			}
		        			else if(array1[i]["method"]=="参军"){
		        				number6[0] =  array1[i]["counts"];
		        			}
		        		} 
		        		console.log("方式分布");
		        		console.log(name);
		        		console.log(sessiondata);
		        		console.log(number1);
		        		console.log(number2);
		        		console.log(number3);
		        		console.log(number4);
		        		console.log(number5);
		        		console.log(number6);
		        		
		        	}
		        }
		    })
	    }
    }
    
	
	//毕业生总人数柱状
	var allpeopleChart = echarts.init(document.getElementById('allpeople-chart'),'macarons');
	function people(yeardata,numberdata){
		allpeopleoption = {
				title : {
			        text: '历年毕业生总人数',
			    },
			    color: ['#3398DB'],
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : yeardata,//['2013', '2014', '2015', '2016', '2017'],
			            axisTick: {
			                alignWithLabel: true
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
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
			            name:'毕业总人数',
			            type:'bar',
			            barWidth: '40%',
			            data:numberdata,//[3213,3422,3522,3424,3912],
			            /* label: {
				            normal: {
				                show: true,
				                position: 'insideRight',
				                textStyle: {
				                    color: 'white' //color of value
				                }
				            }
				        }, */
				        label: {
				            normal: {
				                show: true,
				                position: 'top' //值显示
				            }
				        },
			        }
			    ]
			};
		allpeopleChart.setOption(allpeopleoption);
	}
	
	//就业率柱状
	var employChart = echarts.init(document.getElementById('employ-chart'),'macarons');
	function employrete(yeardata,numberdata){
		//app.title = '折柱混合';
		employoption = {
			title : {
		        text: '历年就业率',
		    },
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : yeardata,//['2013', '2013', '2015', '2016', '2017'],
		            axisTick: {
		                alignWithLabel: true,
		                formatter: '{value} %'
		                
		            }
		        }
		    ],
		    yAxis : [
		        {
		            type: 'value',
		            name: '就业率',
		            min: 0,
		            max: 100,
		            interval: 10,
		            axisLabel: {
		                formatter: '{value} %'
		            }
		        }
		    ],
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
		            name:'就业率',
		            type:'bar',
		            barWidth: '40%',
		            data:numberdata,//[87.2,89.2,92.4,95.6,97.3],
		            /* label: {
			            normal: {
			                show: true,
			                position: 'insideRight',
			                textStyle: {
			                    color: 'white' //color of value
			                }
			            }
			        }, */
			        label: {
			            normal: {
			                show: true,
			                position: 'top' //值显示
			            }
			        },
		        }
		    ]
		};
		employChart.setOption(employoption);	
	}
	
	
	//薪资折线图
	var xzChart = echarts.init(document.getElementById('xz-chart'),'macarons');
	function paychart(paydata){
		xzoption = {
			title : {
			        text: '就业年薪分布',
			},
		    color: ['#31cc31', '#00a0e9', '#f603ff', '#00b419', '#5f52a0'],
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        x: 'center',
		        padding: [10, 20, 0, 20],
		        data: ['2013','2014','2015','2016','2017'],
		        selected: {
		            '2013': true,
		            '2014': true,
		            '2015': true,
		            '2016': true,
		            '2017': true,
		        }
		    },
		    grid: {
		        left: '0',
		        right: '3%',
		        bottom: '3%',
		        top: '13%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        splitLine: { //网格线
		            show: true,
		            lineStyle: {
		                color: ['#b1b1b1'],
		                type: 'dashed'
		            }
		        },
		        data: ['<4w', '4w-6w', '6w-8w', '8w-10w', '10-12w','12-15w','15-20w','20-30w','>30w']
		    },
		    yAxis: {
		        splitLine: { //网格线
		            show: true,
					max:100,
		            lineStyle: {
		                color: ['#b1b1b1'],
		                type: 'dashed'
		            }
		        } 
	            /* type: 'value',
	            name: '人数',
	            min: 0,
	            max: 2000,
	            interval: 100,
	            axisLabel: {
	                formatter: '{value} 人'
	            } */
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
		    series: [
		    {
		        name: '2017',
		        type: 'line',
		        data: paydata,//['200', '300', '500', '300', '280', '290', '38', '30', '20'],
		        label: {
		            normal: {
		                show: true,
		                position: 'top' //值显示
		            }
		        },
		    },
		    {
		        name: '2016',
		        type: 'line',
		        data: ['200', '300', '500', '621', '280', '290', '38', '30', '20'],
		        label: {
		            normal: {
		                show: true,
		                position: 'top' //值显示
		            }
		        },
		    },
		    {
		        name: '2015',
		        type: 'line',
		        data: ['100', '200', '600', '200', '160', '130', '20', '6', '5'],
		        label: {
		            normal: {
		                show: true,
		                position: 'top' //值显示
		            }
		        },
		    },
		    {
		        name: '2014',
		        type: 'line',
		        data: ['234', '400', '700', '214', '213', '123', '21', '4', '1'],
		        label: {
		            normal: {
		                show: true,
		                position: 'top' //值显示
		            }
		        },
		    },
		    {
		        name: '2013',
		        type: 'line',
		        data: ['421', '242', '523', '122', '232', '123', '12', '5', '5'],
		        label: {
		            normal: {
		                show: true,
		                position: 'top' //值显示
		            }
		        },
		    }
		    ]
		};
		xzChart.setOption(xzoption);
	}
	
	
	
	//去向
	var qxChart = echarts.init(document.getElementById('qx-chart'),'macarons');
//app.title = '折柱混合';
	qxoption = {
		title : {
	        text: '就业方式对比',
		},
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            }
	        }
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    legend: {
	        data:['签三方','签合同','升学','出国','自主创业','参军']
	    },
	    xAxis: [
	        {
	            type: 'category',
	            data: ['2013年','2014年','2015年','2016年','2017年'],
	            axisPointer: {
	                type: 'shadow'
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '人数',
	            min: 0,
	            max: 2000,
	            interval: 100,
	            axisLabel: {
	                formatter: '{value} 人'
	            }
	        },
	        /* {
	            type: 'value',
	            name: '温度',
	            min: 0,
	            max: 25,
	            interval: 5,
	            axisLabel: {
	                formatter: '{value} °C'
	            }
	        } */
	    ],
	    series: [
	        {
	            name:'签三方',
	            type:'bar',
	            data:[1100,1300,1500,1363,1642]
	        },
	        {
	            name:'签合同',
	            type:'bar',
	            data:[870,634,532,532,542]
	        },
	        {
	            name:'升学',
	            type:'bar',
	            //yAxisIndex: 1,
	            data:[103,231,267,321,342]
	        },
	        {
	            name:'出国',
	            type:'bar',
	            //yAxisIndex: 1,
	            data:[13,23,26,32,34]
	        },
	        {
	            name:'自主创业',
	            type:'bar',
	            //yAxisIndex: 1,
	            data:[13,31,67,21,42]
	        },
	        {
	            name:'参军',
	            type:'bar',
	            //yAxisIndex: 1,
	            data:[3,21,27,31,29]
	        }
	    ]
	};
	qxChart.setOption(qxoption);
  
</script>

</body>

</html>
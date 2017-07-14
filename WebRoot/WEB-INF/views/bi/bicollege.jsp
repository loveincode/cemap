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
</style>
<body class="gray-bg">
    <div class="wrapper wrapper-content">
    	
    	<font color="red">
			PS：请选择需要查看的学院查看学院就业情况分析
		</font>
    	<!-- ************************* Search Form Start  ************************* -->
    	<div class="row">
    		
    		<div class="col-md-3">
				<div class="form-group">
						<label class="col-lg-3 control-label" style="padding-left:5px;padding-top:6px;">学院：</label>
						<div class="col-lg-9">
							<select name="scollegeId" id="scollegeId" class="form-control">
								<option value="-1">请选择</option>
		        			 	<c:forEach items="${colleges}" var="college">
		        				<option value="${college.id}">${college.name}</option>
		        				</c:forEach>  	 	 
	      					</select>
						</div>
					</div>
			</div>
			
			<div class="col-md-3">
				<div class="form-group">
						<label class="col-lg-3 control-label" style="padding-left:5px;padding-top:6px;">届数：</label>
						<div class="col-lg-9">
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
       <!-- 学历人数 -->
       <div class="row">
       		<!-- 学历人数 -->
       		<div class="col-md-5 col-md-offset-1 chart" id="people-chart"></div>
       		<!-- 男女人数 -->
       		<div class="col-md-5 chart" id="sex-chart"></div>
       </div>
       <br/>
	   <br/>
       <div class="row">
       		<!-- 就业分析 -->
       		<div class="col-md-5 col-md-offset-1 chart" id="employ-chart"></div>
       		<!-- 就业方式 -->
       		<div class="col-md-5 chart" id="method-chart"></div>
       </div>
       <br/>
	   <br/>
       <div class="row">
       		<!-- 单位性质 -->
       		<div class="col-md-10 col-md-offset-1 companynature-chart" id="companynature-chart"></div>
       </div>
       <br/>
	   <br/>
       <div class="row">
       		<!-- 行业单位 -->
       		<div class="col-md-10 col-md-offset-1 hy-chart" id="hy-chart"></div>
       </div>
       <br/>
	   <br/>
	   <div class="row">
       		<!-- 单位前十 -->
       		<div class="col-md-10 col-md-offset-1 chart" id="q10-chart"></div>
       </div>
       <br/>
	   <br/>
       <div class="row">
       		<!-- 薪资分布 -->
       		<div class="col-md-10 col-md-offset-1 chart" id="xz-chart"></div>
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
    
    function getYear(){
    	d = new Date();
    	nowYear = d.getFullYear();
    	return nowYear;
    }
    
    //综合搜索
    $("#query-btn").on("click",function(){
		sgraduationSession = $("#sgraduationSession").val();
		scollegeId = $("#scollegeId").val();
    	Bionload(sgraduationSession,scollegeId);
    });
    
    console.log("当前年份："+getYear());
    //Bionload(getYear());
    function Bionload(session,collegeId){
    	if(session!=-1){
    		console.log(session);
	    	$.ajax
		    ({ 
		        url: "${ctxStatic}/bischool/data",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        	collegeId:collegeId,
		        },
		        success: function (data) {
		        		//alert(data.message);
		        		pie(session,data.data.all,data.data.zhuan,data.data.ben,data.data.yan);
		        		sexpie(data.data.all,data.data.man,data.data.woman);
		            	// window.location.reload();
		        }
		    })
		    
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bischool/employdata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        	collegeId:collegeId,
		        },
		        success: function (data) {
		        		//alert(data.message);
		        		employpie(session,data.data.hasemploy,data.data.noemploy);
		            	// window.location.reload();
		        }
		    })
		    
		    //methoddata
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bischool/methoddata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        	collegeId:collegeId,
		        },
		        success: function (data) {
		        		//alert(data.message);
		        		array = data.data;
		        		console.log(array);
		        		var name = new Array();
		        		var number =  new Array();
		        		var dataall  = new Array();
		        		for(var i = 0;i<array.length;i++){
		        			name[i] = array[i]["method"];
		        			number[i] = array[i]["counts"];
		        			//'签三方','签合同','升学','出国','自主创业','参军'
		        			//{value:man, name:'男'},
		        			if(array[i]["method"] == "签就业协议书"){
		        				name[i] = "签三方";
		        				var obj = {};
		        				obj['value'] = array[i]["counts"];
		        				obj['name'] = "签三方";
		        				dataall[i] = obj;
		        			}
		        			else{
		        				var obj = {};
		        				obj['value'] = array[i]["counts"];
		        				obj['name'] = array[i]["method"];
		        				dataall[i] = obj;
	        				}
		        		}
		        		console.log(dataall);
		        		methodpie(dataall);
		        }
		    })
		    //naturedata
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bischool/naturedata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        	collegeId:collegeId,
		        },
		        success: function (data) {
		        		array = data.data;
		        		console.log(array);
		        		var name = new Array();
		        		var number =  new Array();
		        		for(var i = 0;i<array.length;i++){
		        			name[i] = array[i]["method"];
		        			number[i] = array[i]["counts"];
		        		}
		        		console.log(name,number);
		        		naturechart(name,number);
		        }
		    })
		    //industrydata
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bischool/industrydata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        	collegeId:collegeId,
		        },
		        success: function (data) {
		        		array = data.data;
		        		console.log(array);
		        		var name = new Array();
		        		var number =  new Array();
		        		for(var i = 0;i<array.length;i++){
		        			name[i] = array[i]["method"];
		        			number[i] = array[i]["counts"];
		        		}
		        		console.log(name,number);
		        		industrychart(name,number);
		        }
		    })
		    //companynamedata
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bischool/companynamedata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        	collegeId:collegeId,
		        },
		        success: function (data) {
		        		array = data.data;
		        		console.log(array);
		        		var name = new Array();
		        		var number =  new Array();
		        		for(var i = 0;i<array.length;i++){
		        			name[i] = array[i]["method"];
		        			number[i] = array[i]["counts"];
		        		}
		        		console.log(name,number);
		        		companynamechart(name,number);
		        }
		    })
		    $.ajax
		    ({ 
		        url: "${ctxStatic}/bischool/paydata",
		        method: 'GET',
		        dataType: "json", 
		        data: { 
		        	session:session,
		        	collegeId:collegeId,
		        },
		        success: function (data) {
		        		//alert(data.message);
		        		console.log(data);
		        		var paydata = [data.data.less4,data.data.more4less6,data.data.more6less8,
		        		data.data.more8less10,data.data.more10less12,data.data.more12less15,
		        		data.data.more15less20,data.data.more20less30,data.data.more30];
		        		console.log(paydata);
		        		paychart(paydata);
		            	// window.location.reload();
		        }
		    })
	    }
    }
    
    
    // 
    var myPieChart = echarts.init(document.getElementById('people-chart'),'macarons');
    function pie(jieshu,all,zhuan,ben,yan,shuo,bo,bohou){
		option = {
		    title : {
		        text: '毕业生人数学历比例',
		        subtext: '毕业生总数 '+all+'人 专科：'+zhuan+' 本科：'+ben+' 研究生：'+yan+'',
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
		            name:'学历比例',
		            type:'pie',
		            radius : [30, 110],
		            center : ['50%', '50%'],
		            roseType : 'area',
		            data:[
		                {value:zhuan, name:'专科'},
		                {value:ben, name:'本科'},
		                {value:yan, name:'研究生'},
		            ]
		        }
		    ]
		};		
		myPieChart.setOption(option);
	}
	
	var sexChart = echarts.init(document.getElementById('sex-chart'),'macarons');
	function sexpie(all,man,woman){
		sexoption = {
		    title : {
		        text: '性别比例',
		        subtext: '总 '+all+'人 男：'+man+' 女：'+woman+'',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        x : 'center',
			    y : 'bottom',
		        data: ['男','女']
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
		            name: '性别比例',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:[
		                {value:man, name:'男'},
		                {value:woman, name:'女'},
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		sexChart.setOption(sexoption);	
	}	
	
	//
	var employChart = echarts.init(document.getElementById('employ-chart'),'macarons');
	function employpie(all,hasemploy,noemploy){
		var data = [{
	    	value: hasemploy,
		    name: '已就业'
		}, {
		    value: noemploy,
		    name: '未就业'
		}];
		employoption = {
		    //backgroundColor: '#fff',
		    title : {
		        text: '就业率',
		        /* subtext: '已就业：'+hasemploy+' 未就业：'+noemploy+'', */
		        x:'center',
		        y: 'center',
		    },
		    /* title: {
		        text: '就业情况',
		        subtext: '2017届',
		        x: 'center',
		        y: 'center',
		        textStyle: {
		            fontWeight: 'normal',
		            fontSize: 16
		        }
		    }, */
		    tooltip: {
		        show: true,
		        trigger: 'item',
		        formatter: "{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'horizontal',
		        bottom: '0%',
		        data: ['已就业', '未就业']
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
		    series: [{
		        type: 'pie',
		        selectedMode: 'single',
		        radius: ['25%', '58%'],
		        color: ['#86D560', '#AF89D6', '#59ADF3', '#FF999A', '#FFCC67'],
		
		        label: {
		            normal: {
		                position: 'inner',
		                formatter: '{d}%',
		
		                textStyle: {
		                    color: '#fff',
		                    fontWeight: 'bold',
		                    fontSize: 14
		                }
		            }
		        },
		        labelLine: {
		            normal: {
		                show: false
		            }
		        },
		        data: data
		    }, {
		        type: 'pie',
		        radius: ['58%', '83%'],
		        itemStyle: {
		            normal: {
		                color: '#F2F2F2'
		            },
		            emphasis: {
		                color: '#ADADAD'
		            }
		        },
		        label: {
		            normal: {
		                position: 'inner',
		                formatter: '{c}人',
		                textStyle: {
		                    color: '#777777',
		                    fontWeight: 'bold',
		                    fontSize: 14
		                }
		            }
		        },
		        data: data
		    }]
		};
		employChart.setOption(employoption);
	}
	
	
	var methodChart = echarts.init(document.getElementById('method-chart'),'macarons');
	function methodpie(dataall){
		methodoption = {
		    title : {
		        text: '就业形式',
		        subtext: '不同的就业方式',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        x : 'center',
		        y : 'bottom',
		        data:['签三方','签合同','升学','出国','自主创业','参军'],
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
		            name:'学历比例',
		            type:'pie',
		            radius : [30, 110],
		            center : ['50%', '50%'],
		            roseType : 'area',
		            data:dataall
		        }
		    ]
		};		
		methodChart.setOption(methodoption);
	}	
	
	//单位性质柱状
	var companynatureChart = echarts.init(document.getElementById('companynature-chart'),'macarons');
	function naturechart(namedata,numberdata){
		companynatureoption = {
			title : {
		        text: '单位性质分布',
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
		            data : namedata,//['国有企业', '民营企业', '党政机关', '高等教育单位', '事业单位', '三资企业', '科研设计','中初教育单位','部队单位','医疗卫生单位'],
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
		            name:'就业人数',
		            type:'bar',
		            barWidth: '20%',
		            data:numberdata,//[10, 52, 200, 334, 390, 330, 220,8,9,10]
		        }
		    ]
		};
		companynatureChart.setOption(companynatureoption);
	}
	//行业
	var hyChart = echarts.init(document.getElementById('hy-chart'),'macarons');
	//app.title = 'Top 10';
	function industrychart(namedata,numberdata){
		hyoption = {
			title : {
		        text: '就业单位行业分布',
		        x:'center'
		    },
		    //color: ['#3398DB'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: { // 坐标轴指示器，坐标轴触发有效
		            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    yAxis: [{
		        type: 'category',
		        data:namedata, 
		        /*['金融业', '公共管理、社会保障和社会组织', '信息传输、软件和信息技术服务业', '教育', '科学研究和技术服务业', '文化、体育和娱乐业', '租赁和商务服务业',
		        '制造业', '交通运输、仓储和邮政业','房地产业','电力、热力、燃气及水生产和供应业','建筑业','批发和零售业','水利、环境和公共设施管理业','军队','卫生和社会工作','采矿业',
		        '农、林、牧、渔业','居民服务、修理和其他服务业','住宿和餐饮业'],*/
		        axisTick: {
		            alignWithLabel: true
		        }
		    }],
		    xAxis: [{
		        type: 'value'
		    }],
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
		    //backgroundColor: '#ffffff',
		    series: [{
		        name: '就业人数',
		        type: 'bar',
		        data: numberdata,//[132,231,1223,12,234,23,135,231,312,123,12,31,3,123,123,1,23,312,3,12],
		        label: {
		            normal: {
		                show: true,
		                position: 'insideRight',
		                textStyle: {
		                    color: 'white' //color of value
		                }
		            }
		        },
		        itemStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
		                    offset: 0,
		                    color: 'lightBlue' // 0% 处的颜色
		                }, {
		                    offset: 1,
		                    color: '#3398DB' // 100% 处的颜色
		                }], false)
		            }
		        }
		    }]
		};	
		hyChart.setOption(hyoption);
	}	
	//薪资折线图
	var xzChart = echarts.init(document.getElementById('xz-chart'),'macarons');
	function paychart(paydata){
		xzoption = {
		    color: ['#31cc31', '#00a0e9', '#f603ff', '#00b419', '#5f52a0'],
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        x: 'left',
		        padding: [10, 20, 0, 20],
		        data: ['就业薪资分布'],
		        selected: {
		            '就业薪资分布': true,
		            
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
		            lineStyle: {
		                color: ['#b1b1b1'],
		                type: 'dashed'
		            }
		        }
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
		    series: [{
		        name: '就业薪资分布',
		        type: 'line',
		        data: paydata,//['200', '300', '500', '300', '280', '290', '38', '30', '20'],
		        label: {
		            normal: {
		                show: true,
		                position: 'top' //值显示
		            }
		        }
		    }]
		};
		xzChart.setOption(xzoption);
	}
	
	//行业
	var q10Chart = echarts.init(document.getElementById('q10-chart'),'macarons');
	//app.title = 'Top 10';
	function companynamechart(namedata,numberdata){
		q10option = {
			title : {
		        text: '单位去向前10',
		        x:'center'
		    },
		    //color: ['#3398DB'],
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: { // 坐标轴指示器，坐标轴触发有效
		            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    yAxis: [{
		        type: 'category',
		        data: namedata,//['第十','第九','第八','第七','第六','第五','第四','第三','第二','第一'],
		        axisTick: {
		            alignWithLabel: true
		        }
		    }],
		    xAxis: [{
		        type: 'value'
		    }],
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
		    //backgroundColor: '#ffffff',
		    series: [{
		        name: '就业人数',
		        type: 'bar',
		        data: numberdata,//[10,20,30,40,50,60,70,80,90,100],
		        label: {
		            normal: {
		                show: true,
		                position: 'insideRight',
		                textStyle: {
		                    color: 'white' //color of value
		                }
		            }
		        },
		        itemStyle: {
		            normal: {
		                color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
		                    offset: 0,
		                    color: 'lightBlue' // 0% 处的颜色
		                }, {
		                    offset: 1,
		                    color: '#3398DB' // 100% 处的颜色
		                }], false)
		            }
		        }
		    }]
		};	
		q10Chart.setOption(q10option);
	}
  

</script>

</body>

</html>
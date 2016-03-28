function UrlMap(flow, secao, funcao){
					
					this.flow = flow;
					this.secao = secao;
					this.funcao = funcao;
				}
			 
				jQuery(document).ready(function(){
					
				//	jQuery('a').click(function(){

							var params = window.location.search.substring(1).split('&');
							
							 var sectionName;
							    var flowName;
							    var role;
							   
							for(i = 0; i < params.length; i++){ 

							    var arg = params[i].split('='); 
							   
							    if(arg[0] == 'sectionName'){
							    	
							    	sectionName = arg[1];
							    }
							    
							    if(arg[0]=='flowName'){
							    	
							    	flowName = arg[1];
							    }
							    
// 							    if(obj.key == 'sectionName'){ 
							    	
							    	
// 							    	jQuery.ajax({
// 							    		url: "http://usage.infoway-pi.com.br/usage",
// 							    		method: 'POST',
// 							    		data: obj,
// 							    		beforeSend:function(){ console.log("enviando .. "+obj);},
// 							    		success:function(){ console.log("enviado!"); }
// 							    	});
							    	
// 							    	if(typeof(Storage) !== "undefined") {
// 							    		if(localStorage.getItem('lnk') == null){
// 								    		var a = [];
// 								    		a.push(JSON.parse(localStorage.getItem('lnk')));
// 								    		localStorage.setItem('lnk', JSON.stringify(a));
// 							    		}
							    		
// 							    		var a = [];
// 							    	    a = JSON.parse(localStorage.getItem('lnk'));
// 							    	    a.push(obj);
// 							    	    localStorage.setItem('lnk', JSON.stringify(a));
// 							    	}
// 					    		}
							    
							} 
							var obj = new UrlMap(flowName, sectionName, "${usuario.role}");
						    console.log(obj);

//						    	jQuery.ajax({
//						    		url: "http://usage.infoway-pi.com.br/usage",
//						    		method: 'POST',
//						    		data: obj,
//						    		beforeSend:function(){ console.log("enviando .. "+obj);},
//						    		success:function(){ console.log("enviado!"); }
//						    	});
						    
						    if(typeof(Storage) !== "undefined") {
						    		if(localStorage.getItem('lnk') == null){
							    		var a = [];
							    		a.push(JSON.parse(localStorage.getItem('lnk')));
							    		localStorage.setItem('lnk', JSON.stringify(a));
						    		}
					    		
						    		var a = [];
						    	    a = JSON.parse(localStorage.getItem('lnk'));
						    	    a.push(obj);
						    	    localStorage.setItem('lnk', JSON.stringify(a));
					    	}
							
					//});

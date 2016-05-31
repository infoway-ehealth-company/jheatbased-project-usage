/**
 * 
 * Variáveis globais 'urlCorrente' e 'splitUrlParamsBy' estão definidas no arquivo 'view/scripts/javascript-utils.js'
 * que por sua vez é importado no arquivo 'view/jsp/includes/JavaScripts.jsp', antes de 'jhbu.plugin.js'
 */ 

var splitUrlParamsBy = '=';

(function ( $ ) {
	
	$.fn.jhbumap = function(options) {
		
		var sectionName;
		var featureName;	   
		
		var flowName;
		var className;
		var reportName;
		
		var settings = $.extend({
          		 app_url: window.location,
           		 params: window.location.search.substring(1).split('&'),
			 user: "",
			 role: "",
			 projId: "",
			 sectionNameVar:'sectionName',
  			 flowNameVar:'flowName',
  			 classNameVar:'className',
  			reportNameVar:'reportName'
       		 }, options );	
		
		params = settings.params;
		for(i = 0; i < params.length; i++){ 
			
			/*
			 * extraindo os parâmetros da url
			 */
		    var arg = params[i].split(splitUrlParamsBy); 
		    if(arg[0] == settings.sectionNameVar){
		    	sectionName = arg[1];
		    }if(arg[0]== settings.flowNameVar 
		    		|| arg[0]== settings.classNameVar
		    		|| arg[0]== settings.reportNameVar){
		    	featureName = arg[1];
		    }
		}  
		    /*
		     * Setando a feature que está sendo usada, por meio de sua url: se sectionName é definida na url, mas o featureName 
		     * não é definido, isso significa que a funcionalidade corrente (sendo usada) é a listagem de fluxos da seção;
		     * se o featureName é definido, isso significa que uma nova funcionalidade está sendo acessada. 
		     */
		    if(sectionName){
		    	if(featureName){
		    		urlCorrente = new UrlMap(featureName, sectionName, options.role);
		    		 if(typeof(Storage) !== "undefined") {
				    	localStorage.setItem("urlCorrente",JSON.stringify(urlCorrente));
			    	}else {
			    		console.log("local storage not defined");
			    	}
		    	} else {
		    		localStorage.setItem("urlCorrente", null);
		    	}
		    }
		    
		    /*
		     * Enviando requisição POST quando o fluxo de uma funcionalidade é finalizado.
		     * Esse trecho é baseado na view: views onde uma operação foi finalizada, não apresentam ações (butões) 
		     * nomeados como 'insert', 'search', 'submit' e 'reportexecute' 
		     */
		    var insert = jQuery("button[name='insert']");
		    var search = jQuery("button[name='search']");
		    var submit = jQuery("button[name='submit']");
		    var reportexecute = jQuery("button[name='reportexecute']");
		    
		    if(typeof(Storage) !== "undefined") {
		    	var urlCorrente = localStorage.getItem("urlCorrente");
	    	}
		    if(urlCorrente){
			    if(!insert.length && !search.length && !submit.length && !reportexecute.length){
			    	console.log("sending");
//			    	var obj = new PostObj(options.projId, JSON.parse(urlCorrente));
			    	var queryParams = "project-id="+options.projId+"&data="+urlCorrente;
			    	console.log(queryParams);
			    	jQuery.ajax({
			    		url : "http://localhost:8081/jhbu/usage?"+queryParams,
			    		type : 'POST',
			    		beforeSend : function() {
			    			console.log("enviando .. " + obj);
			    		},
			    		success : function() {
			    			console.log("enviado!");
			    		}
			    	});
			    }		
		    }
		return this;
 	};
}( jQuery ));

function PostObj(projId, data){
	this.projId = projId;
	this.data = data;
}

function UrlMap(fluxo, secao, funcao){
	
	this.fluxo = fluxo;
	this.secao = secao;
	this.funcao = funcao;

}

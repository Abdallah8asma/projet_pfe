'use strict';

var csvImport = angular.module('ngCsvImport', []);

csvImport.directive('ngCsvImport', function() {
	return {
		restrict: 'E',
		transclude: true,
		replace: true,
		scope:{
			content:'=?',
			header: '=?',
			headerVisible: '=?',
			separator: '=?',
			separatorVisible: '=?',
			result: '=?',
			encoding: '=?',
			encodingVisible: '=?',
			accept: '=?'
		},
		template: '<div style="margin-top: -10px; max-height: 100%; padding-top: 0%;">'+
	    	'<label for="files"> <span class="btn">Import CSV</span></label>'+
	    	'<input style="visibility: hidden; position: absolute;" id="files" class="form-control" type="file" name="files">'+
			'</div>',
		link: function(scope, element) {
			scope.separatorVisible = scope.separatorVisible || false;
			scope.headerVisible = scope.headerVisible || false;

			angular.element(element[0].querySelector('.separator-input')).on('keyup', function(e) {
				if ( scope.content != null ) {
					var content = {
						csv: scope.content,
						header: scope.header,
						separator: e.target.value,
						encoding: scope.encoding
					};
					scope.result = csvToJSON(content);
					scope.$apply();
				}
			});

			element.on('change', function(onChangeEvent) {
				console.log("New Files insered!");
				var reader = new FileReader();
				scope.filename = onChangeEvent.target.files[0].name;
				reader.onload = function(onLoadEvent) {
					scope.$apply(function() {
						var content = {
							csv: onLoadEvent.target.result.replace(/\r\n|\r/g,'\n'),
							header: scope.header,
							separator: scope.separator
						};
						scope.content = content.csv;
						scope.result = csvToJSON(content);
						scope.result.filename = "";
					});
					console.log("Result after Apply "+scope.result  );
					angular.element("input[type='file']").val(null);
				};

				if ( (onChangeEvent.target.type === "file") && (onChangeEvent.target.files != null || onChangeEvent.srcElement.files != null) )  {
					reader.readAsText((onChangeEvent.srcElement || onChangeEvent.target).files[0], scope.encoding);
				} else {
					if ( scope.content != null ) {
						var content = {
							csv: scope.content,
							header: !scope.header,
							separator: scope.separator
						};
						scope.result = csvToJSON(content);
					}
				}
			});

			var csvToJSON = function(content) {
				console.log("csvToJSON function called!");
				var lines=content.csv.split('\n');
				var result = [];
				var start = 0;
				var columnCount = lines[0].split(content.separator).length;

				var headers = [];
				if (content.header) {
					headers=lines[0].split(content.separator);
					start = 1;
				}
				console.log("Nuber of lines "+lines.length);
				for (var i=start; i<lines.length; i++) {
					var obj = {};
					var currentline=lines[i].split(new RegExp(content.separator+'(?![^"]*"(?:(?:[^"]*"){2})*[^"]*$)'));
					if ( currentline.length === columnCount ) {
						if (content.header) {
							for (var j=0; j<headers.length; j++) {
								obj[headers[j]] = currentline[j];
							}
						} else {
							for (var k=0; k<currentline.length; k++) {
								obj[k] = currentline[k];
							}
						}
						result.push(obj);
					}
				}
				console.log("Result: "+JSON.stringify(result, null, Number(2))   );
				console.log("Result after csvToJson"  );
				console.log("--ref : "+JSON.stringify(result['0']['1'], null, "  ")   );
				return result;
			};
		}
	};
});
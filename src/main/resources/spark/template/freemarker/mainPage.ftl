<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Vikiprojekts:Latvijas ciemi | LĢIA dati">

<h3><a href="https://lv.wikipedia.org/wiki/Vikiprojekts:Latvijas_ciemi">Vikiprojekts:Latvijas ciemi</a></h3>
<p class="explain">Rīks Vikipēdijas aizmetņa izveidei par apdzīvotu vietu no LĢIA vietvārdu datubāzes datiem.</p>

<#if message??>
	<div class="message">${message}</div>
</#if>

<div class="top-container">
<div class="form-container">
<form class="form-inline" action="/lgia-extract" method="GET">
<div class="form-group">
	<input type="checkbox" name="otheruses" id="otheruses" value="1"<#if otherUses??> checked</#if>/>
	<label for="otheruses">Pievienot veidni "citas nozīmes"</label>
</div><br />
<div class="form-group">
    <label for="id"><a href="http://vietvardi.lgia.gov.lv/vv/to_www.sakt">LĢIA ID</a></label>
	<input class="form-control" type="text" id="id" name="id" value="<#if id??>${id}</#if>">    
</div>
<button class="btn btn-default" type="submit">Iesniegt</button>
</form>
</div>

<#if name??>
<ul class="links">
	<li><a href="${url}">LĢIA</a></li>
	<li><a href="https://www.google.lv/maps/place/${name},+${parish}">Google Maps (ciems)</a></li>
	<li><a href="https://www.google.lv/maps/place/${parish}">Google Maps (pagasts)</a></li>
	<li><a href="http://www.kurtuesi.lv/maps">Kurtuesi</a> (augstums no topokartes)</li>
	<li><a href="http://balticmaps.eu/">Baltic Maps</a></li>
	<li><a href="https://www.google.lv/images?safe=off&tbm=isch&tbs=sur:fmc&q=%22${name}%22+-site:wikipedia.org+-site:wikimedia.org">Google Images</a> (meklēt brīvus attēlus)</li>
	<li><a href="http://saraksts.mantojums.lv/lv/piemineklu-saraksts/?t=${name}&region=0&group=0&type=0">VKPAI saraksts</a></li>	
</ul>
</div>

<div class="copybutton">
<button class="btn js-copybtn">Kopēt starpliktuvē</button>
</div>

<pre class="wikitext" id="wikitext"><#if otherUses??>{{citas nozīmes|apdzīvotu vietu ${parish_loc}|${name}|${name}}}</#if>
{{Apdzīvotas vietas infokaste
| name                     = ${name}
| settlement_type          = ${type}
| image_skyline            = 
| image_caption            = 
| pushpin_map              = Latvija
| pushpin_label_position   = 
| subdivision_type         = Valsts
| subdivision_type1        = Novads
| subdivision_type2        = Pagasts
| subdivision_name         = {{LAT}}
| subdivision_name1        = [[${municipality}]]
| subdivision_name2        = [[${parish}]]
| established_title        = &lt;!-- Pirmoreiz minēts --&gt;
| established_date         = &lt;!-- datums, kad pirmoreiz minēts --&gt;
| area_total_km2           = 
| area_land_km2            = &lt;!-- var ielikt teritoriālplānošanā noteikto platību --&gt;
| population_as_of         = ${population_date}
| population_footnotes     = &lt;ref name="lgia"&gt;{{Tīmekļa atsauce |url=${url} |title=Informācija par objektu: ${name} |accessdate=${date?string("{{'dat'|yyyy|MM|dd||'bez'}}")} |work= LĢIA vietvārdu datubāze |publisher=[[Latvijas Ģeotelpiskās informācijas aģentūra]] }}&lt;/ref&gt;
| population_total         = ${population}
| population_density_km2   = &lt;!-- apdzīvotības blīvums --&gt;
| latd  = ${lat_deg} | latm  = ${lat_min} | lats  = ${lat_sec} | latNS  = N
| longd = ${lon_deg} | longm = ${lon_min} | longs = ${lon_sec} | longEW = E
| elevation_m              = 
| website                  = &lt;!-- mājaslapa; ja atšķirīga no pagasta/novada lapas --&gt;
| postal_code_type         = Pasta nodaļa
| postal_code              = ${post_code}
| footnotes                = &lt;!-- specpiezīmes --&gt;
}}

'''${name}''' ir ciems [[${municipality}|${municipality_gen}]] [[${parish}|${parish_loc}]]. Izvietojies pagasta &lt;!-- izvēlies vienu --&gt; ziemeļu dienvidu austrumu rietumu centrālajā daļā Z km no pagasta centra [[XXXX]]s, XX km no novada centra [[${municipality_adm_center}]]s un YYY km no [[Rīga]]s.

Apdzīvotā vieta izvietojusies pie autoceļa [[PYYYY]], [[ZZZZ]] upes ezera krastā.

== Atsauces ==
{{atsauces}}


{{Latvijas ģeogrāfija-aizmetnis}}

{{${parish_gen} ciemi}}
</pre>	

<script>
var copyTextareaBtn = document.querySelector('.js-copybtn');

copyTextareaBtn.addEventListener('click', function(event) {
  selectText('wikitext');
  try {
    document.execCommand('copy');
  } catch (err) {
    console.log('Oops, unable to copy');
  }
});

function selectText(containerid) {
    if (document.selection) {
        var range = document.body.createTextRange();
        range.moveToElementText(document.getElementById(containerid));
        range.select();
    } else if (window.getSelection()) {
        var range = document.createRange();
        range.selectNode(document.getElementById(containerid));
        window.getSelection().removeAllRanges();
        window.getSelection().addRange(range);
    }
}
</script>
</#if>
</@layout.masterTemplate>
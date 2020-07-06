//tslint:disable
declare var $: any;
export class Headers {

  public static lieuxHeader(rowClick){
    return{
      groupHeader:function(value, count, data, group){
        return value + "<span style='color:#d00; margin-left:10px;'>(" + count + " elements)</span>";
      },
      placeholder:"La liste des adresses est vide.",
      cellClick: rowClick,
      paginationSize:15,
      layout:"fitColumns",
      responsiveLayout:"collapse",
      groupBy:["region"],
      langs:{"fr-fr":{"pagination":{
        "first":"Premier",
        "first_title":"Premier Page",
        "last":"Dernier",
        "last_title":"Dernier Page",
        "prev":"Précédent",
        "prev_title":"Précédent Page",
        "next":"Prochain",
        "next_title":"Prochain Page",
      }}},
      columns:[
        {formatter:"responsiveCollapse", width:30, minWidth:30, align:"center", resizable:false, headerSort:false},

        {title:"id", field:"id", align:"center", headerFilter:true},
        {title:"numero", field:"numero",  align:"center", headerFilter:true},
        {title:"rue", field:"rue",  align:"center", headerFilter:true},
        {title:"complement", field:"complement",  align:"center", headerFilter:true},
        {title:"lat", field:"lat",  align:"center", headerFilter:true},
        {title:"lng", field:"lng",  align:"center", headerFilter:true},
        {
          title:"Lieux",
          field:"lieux",
          align:"center",
          columns:[
            {title:"ville", field:"ville", align:"center", headerFilter: true},
            {title:"postale", field:"postale", align:"center", headerFilter: true}
          ]
        },
        {title:"region", field:"region", align:"center", headerFilter: true},
        {title:"", field:"delete", align:"center", formatter: function(cell, fp){return "<i class='trash alternate outline icon'></i>"}}
      ]
    }
  }
  public static localHeader(rowClick){
    return {
      groupHeader:function(value, count, data, group){
        return value + "<span style='color:#d00; margin-left:10px;'>(" + count + " elements)</span>";
      },
      placeholder:"La liste des appartements est vide.",
      cellClick: rowClick,
      layout:"fitColumns",
      pagination:"local",
      paginationSize:15,
      groupBy:["achat"],
      langs:{"fr-fr":{"pagination":{
        "first":"Premier",
        "first_title":"Premier Page",
        "last":"Dernier",
        "last_title":"Dernier Page",
        "prev":"Précédent",
        "prev_title":"Précédent Page",
        "next":"Prochain",
        "next_title":"Prochain Page",
      }}},
      columns:[
          {title:"id", field:"id", width:80,  align:"center", headerFilter:true},
          {title:"titre", field:"titre",  align:"center", headerFilter:true},
          {title:"date publication", field:"pub",  align:"center",headerFilter:true},
          {title:"description", field:"desc",  align:"center", headerFilter:true},
          {title:"disponible", field:"dispo",  formatter:"tickCross", align:"center", headerFilter:true},
          {title:"chambres", field:"chambres",  align:"center", headerFilter:true},
          {title:"pieces", field:"pieces", align:"center", headerFilter:true},
          {title:"prix", field:"prix", align:"center", headerFilter:true, formatter: function(cell, fp){return cell.getValue() + "<i class='euro sign icon'></i>"}},
          {title:"achat/loue", field:"achat", align:"center", headerFilter:true},
          {title:"surface", field:"surface", align:"center", headerFilter:true},
          {title:"surface terrain", field:"terrain", align:"center", headerFilter:true},
          {
            title:"Adresse",
            align:"center",
            field: "adr",
            columns:[
            {title:"adresse", field:"adresse", align:"center", headerFilter:true},
            {title:"ville", field:"ville", align:"center", headerFilter:true},
            {title: "postal", field:"postal", align:"center", headerFilter:true},
          ]
          },
          {title:"", field:"delete", align:"center", formatter: function(cell, fp){return "<i class='trash alternate outline icon'></i>"}}
      ],
    }
  }

}

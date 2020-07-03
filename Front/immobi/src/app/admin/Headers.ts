//tslint:disable
export class Headers {
  public static localHeader(rowClick){
    return {
      groupHeader:function(value, count, data, group){
        return value + "<span style='color:#d00; margin-left:10px;'>(" + count + " elements)</span>";
      },
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
          {title:"id", field:"id", width:80,  align:"center"},
          {title:"titre", field:"titre",  align:"center"},
          {title:"date publication", field:"pub",  align:"center"},
          {title:"description", field:"desc",  align:"center"},
          {title:"disponible", field:"dispo",  formatter:"tickCross", align:"center"},
          {title:"chambres", field:"chambres",  align:"center"},
          {title:"pieces", field:"pieces", align:"center"},
          {title:"prix", field:"prix", align:"center", formatter: function(cell, fp){return cell.getValue() + "<i class='euro sign icon'></i>"}},
          {title:"achat/loue", field:"achat", align:"center"},
          {title:"surface", field:"surface", align:"center"},
          {title:"surface terrain", field:"terrain", align:"center"},
          {
            title:"Adresse",
            align:"center",
            field: "adr",
            columns:[
            {title:"adresse", field:"adresse", align:"center"},
            {title:"ville", field:"ville", align:"center"},
            {title: "region", field:"region", align:"center"},
          ]
          },
          {title:"", field:"delete", align:"center", formatter: function(cell, fp){return "<i class='trash alternate outline icon'></i>"}}
      ],
    }
  }

}

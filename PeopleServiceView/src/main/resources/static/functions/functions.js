function resizeView(){
    const height = window.innerHeight-100;
    const content = document.getElementById("content");
    content.style.height = height+"px";
    const menu = document.getElementById("menu");
    menu.style.height = height+"px";
    const data = document.getElementById("data");
    data.style.height = height+"px";
}
async function showAll(){
  const response = await fetch('http://localhost:8081/people/all');
  const myJson = await response.json(); //extract JSON from the http response
  generateHTML(myJson);
}
async function showInfo(id){
  const response = await fetch('http://localhost:8081/people/'+id);
  const myJson = await response.json(); //extract JSON from the http response
  generateInfoHTML(myJson);
}
async function search(){
  const input = document.getElementById("search_input");
  if(input.value){
      const response = await fetch('http://localhost:8081/people/search/'+input.value);
      const myJson = await response.json(); //extract JSON from the http response
      generateHTML(myJson);
  }
}
async function deleteAll(){
    const response = await fetch('http://localhost:8081/people/delete/all');
    const myJson = await response.json();
}
async function deleteById(id){
    const response = await fetch('http://localhost:8081/people/delete/'+id, {method:'DELETE'});
    const myJson = await response.json();
    alert(myJson.message);
}
function generateHTML(people){
  let inner = "";
  let index = 0;
  for(const person of people){
    inner += "<div class='person' id='person"+person.id+"'><h5>" + person.fullName +
        "</h5><button class='btn' onclick='showInfo("+person.id+");'>Show</button>" +
        "<button class='btn' onclick='generateEditHTML("+JSON.stringify(person)+");'>Edit</button>" +
        "<button class='btn' onclick='deleteById("+person.id+"); hideDeleted("+person.id+")'>Delete</button></div>";
  }
  const dataSpace = document.getElementById("data");
  dataSpace.innerHTML = inner;
}
function generateEditHTML(person){
   const modal = document.getElementById("myModal");
   modal.style.display = "block";
   const modalData = document.getElementById("modal_data");

   let inner = "<div id='add_person_form'>";
       inner += "<label for='fullname_input'>Full name:</label></br><input type='text' id='fullname_input' value='"+person.fullName+"'/></br>";


   for(const email of person.email){
        inner += "<label for='email_input'>E-mail:</label></br><input type='text' class='email_input' value='"+email.email+"'/>Type:<select name='email_type' class='email_type_input'><option value='pers'";
        if(email.type == "pers"){
            inner += " selected ";
        }
        inner += ">Personal</option><option value='work'";
        if(email.type == "work"){
            inner += " selected ";
        }
        inner += ">Work</option></select></br>";
   }
   for(const address of person.address){
        inner += "<label for='address_input'>Address:</label></br><input type='text' class='address_input' value='"+address.info+"'/>Type:<select name='address_type' class='address_type_input'><option value='const'";
        if(address.type == "const"){
            inner += " selected ";
        }
        inner += ">Constant</option><option value='curr'";
        if(address.type == "curr"){
            inner += " selected ";
        }
        inner += ">Current</option></select></br>";
   }
   inner += "<label for='pin_input'>Pin:</label></br><input type='password' id='pin_input' value='"+(person.pin == null ? "" : person.pin)+"'/></br>";
   inner += "<label for='repeat_pin_input'>Repeat pin:</label></br><input type='password' id='repeat_pin_input' value='"+(person.pin == null ? "" : person.pin)+"'/></br>";

   inner += "<button class='btn' onclick='update("+person.id+");'>Update</button></div>";
   inner += "</div>";
   modalData.innerHTML = inner;
}

function generateInfoHTML(person){
   let inner = "";
   inner += "<div class='person' id='person"+person.id+"'><h5>" + person.fullName + "</h5>";
   inner += "<h6>emails:</h6>"
   for(const email of person.email){
        inner += email.email;
        if(email.type == "pers"){
            inner += " (personal)";
        }
        else if(email.type == "work"){
            inner += " (work)";
        }
        inner += "</br>";
   }
   inner += "<h6>addresses:</h6>";
   for(const address of person.address){
        inner += address.info;
        if(address.type == "const"){
            inner += " (constant)";
        }
        else if(address.type == "curr"){
            inner += " (current)";
        }
        inner += "</br>";
   }
   inner += "<div id='edit_buttons'><button class='btn' onclick='deleteById("+person.id+"); hideDeleted("+person.id+")'>Delete</button>";
   inner += "<button class='btn' onclick='addEmail("+person.id+");'>Add Email</button>";
   inner += "<button class='btn' onclick='addAddress("+person.id+");'>Add Address</button></div></div>";
  const dataSpace = document.getElementById("data");
  dataSpace.innerHTML = inner;
}
function addEmail(id){
    const modal = document.getElementById("myModal");
    modal.style.display = "block";
    const modalData = document.getElementById("modal_data");
    let inner = "<div id='add_person_form'>";
    inner += "<label for='email_input'>E-mail:</label></br><input type='text' id='email_input'/>Type:<select name='email_type' id='email_type_input'><option value='pers'>Personal</option><option value='work'>Work</option></select></br>";
    inner += "<button onclick='addEmailOrAddress("+id+");'>Add</button>";
    inner += "</div>";
    modalData.innerHTML = inner;
}
function addAddress(id){
    const modal = document.getElementById("myModal");
    modal.style.display = "block";
    const modalData = document.getElementById("modal_data");
    let inner = "<div id='add_person_form'>";
    inner += "<label for='address_input'>E-mail:</label></br><input type='text' id='address_input'/>Type:<select name='address_type' id='address_type_input'><option value='const'>Constant</option><option value='curr'>Current</option></select></br>";
    inner += "<button onclick='addEmailOrAddress("+id+");'>Add</button>";
    inner += "</div>";
    modalData.innerHTML = inner;
}
function hideDeleted(id){
    const div = document.getElementById("person"+id);
    div.style.visibility = "hidden";
    div.style.display = "none";
}
function addNewPerson(){
    let inner = "<div id='add_person_form'>";
    inner += "<label for='fullname_input'>Full name:</label></br><input type='text' id='fullname_input'/></br>";
    inner += "<label for='email_input'>E-mail:</label></br><input type='text' id='email_input'/>Type:<select name='email_type' id='email_type_input'><option value='pers'>Personal</option><option value='work'>Work</option></select></br>";
    inner += "<label for='address_input'>Address:</label></br><input type='text' id='address_input'/>Type:<select name='address_type' id='address_type_input'><option value='const'>Constant</option><option value='curr'>Current</option></select></br>";
    inner += "<label for='pin_input'>Pin:</label></br><input type='password' id='pin_input'/></br>";
    inner += "<label for='repeat_pin_input'>Repeat pin:</label></br><input type='password' id='repeat_pin_input'/></br>";
    inner += "<button onclick='submit();'>Submit</button>";
    inner += "</div>";
    const data = document.getElementById("data");
    data.innerHTML = inner;
}
function addEmailOrAddress(id){
    const email = document.getElementById("email_input");
    const emailType = document.getElementById("email_type_input");
    const address = document.getElementById("address_input");
    const addressType = document.getElementById("address_type_input");
    let jsonString = "{";
    let link = null;
    if(email){
        link = "emails";
        jsonString += '"type":"'+emailType.value+'", "email":"'+email.value+'", "personId":'+id;
    }
    if(address){
        link = "addresses"
        jsonString += '"type":"'+addressType.value+'", "info":"'+address.value+'", "personId":'+id;
    }
    jsonString += "}";
    console.log(jsonString);
    const response = new XMLHttpRequest();
    response.open("POST", 'http://localhost:8081/'+link+'/add');
    response.setRequestHeader('Content-Type', 'application/json');
    response.send(jsonString);
    response.onload = (e) => {
        const jsonResponse = JSON.parse(response.response);
        alert(jsonResponse.message);
        showInfo(id);
        closeModal();
    }
}
function submit(){
    const fullName = document.getElementById("fullname_input").value;
    const email = document.getElementById("email_input").value;
    const emailType = document.getElementById("email_type_input").value;
    const address = document.getElementById("address_input").value;
    const addressType = document.getElementById("address_type_input").value;
    const pin = document.getElementById("pin_input").value;
    const repeatPin = document.getElementById("repeat_pin_input").value;
    if(pin != repeatPin){
        return;
    }
    let jsonString = "";
    if(pin != ""){
        jsonString='{ "fullName":"'+fullName+'", "pin":"'+pin+'", "address":[{"type":"'+addressType+'", "info":"'+address+'"}], "email":[{"type":"'+emailType+'", "email":"'+email+'"}]}';
    }
    else{
        jsonString='{ "fullName":"'+fullName+'", "address":[{"type":"'+addressType+'", "info":"'+address+'"}], "email":[{"type":"'+emailType+'", "email":"'+email+'"}]}';
    }
    const response = new XMLHttpRequest();
    response.open("POST", 'http://localhost:8081/people/add');
    response.setRequestHeader('Content-Type', 'application/json');
    response.send(jsonString);
    response.onload = (e) => {
        const jsonResponse = JSON.parse(response.response);
        alert(jsonResponse.message);
        showAll();
    }
}
function update(id){
    const fullName = document.getElementById("fullname_input").value;
    const emails = document.getElementsByClassName("email_input");
    const emailsType = document.getElementsByClassName("email_type_input");
    const addresses = document.getElementsByClassName("address_input");
    const addressesType = document.getElementsByClassName("address_type_input");
    const pin = document.getElementById("pin_input").value;
    const repeatPin = document.getElementById("repeat_pin_input").value;
    console.log(addressesType);
    if(pin != repeatPin){
        return;
    }
    let jsonString = '{ "id": '+id+', "fullName":"'+fullName+'", ';
    if(pin != "" && pin != null && pin != "null"){
        jsonString +='"pin":"'+pin+'", ';
        // "email":[{"type":"'+emailType+'", "email":"'+email+'"}]}';
    }
    jsonString += '"address":[';
    for(let i = 0; i<addresses.length; i++){
        jsonString += '{"type":"'+addressesType[i].value+'", "info":"'+addresses[i].value+'"}';
        if(i != addresses.length-1){
            jsonString += ', ';
        }
    }
    jsonString += '], ';
    jsonString += '"email":[';
    for(let i = 0; i<emails.length; i++){
        jsonString += '{"type":"'+emailsType[i].value+'", "email":"'+emails[i].value+'"}';
        if(i != emails.length-1){
            jsonString += ', ';
        }
    }
    jsonString += ']}';
    closeModal();
    const response = new XMLHttpRequest();
    response.open("PUT", 'http://localhost:8081/people/update');
    response.setRequestHeader('Content-Type', 'application/json');
    response.send(jsonString);
    response.onload = (e) => {
        const jsonResponse = JSON.parse(response.response);
        alert(jsonResponse.message);
        showAll();
    }
}
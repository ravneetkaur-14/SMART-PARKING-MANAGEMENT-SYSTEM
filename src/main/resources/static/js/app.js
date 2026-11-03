const search=document.querySelector('#slotSearch'), status=document.querySelector('#statusFilter');
function filterSlots(){document.querySelectorAll('.slot-card').forEach(card=>{const code=card.dataset.code.toLowerCase(),state=card.dataset.status;card.style.display=(!search||code.includes(search.value.toLowerCase()))&&(!status||!status.value||state===status.value)?'':'none';});}
if(search)search.addEventListener('input',filterSlots);if(status)status.addEventListener('change',filterSlots);

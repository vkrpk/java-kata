<template>
  <div>
    <h1>Flash Sales</h1>

    <input v-model="customerId" placeholder="Customer ID" />
    <button @click="loadSales">Charger les ventes</button>

    <div v-for="sale in sales" :key="sale.id">
      <h3>{{ sale.productName }}</h3>
      <p>Stock restant : {{ sale.remainingStock }}</p>
      <p>Fin : {{ sale.endTime }}</p>
      <button @click="purchase(sale.id)">Acheter</button>
    </div>

    <p v-if="message">{{ message }}</p>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const customerId = ref('')
const sales = ref([])
const message = ref('')

async function loadSales() {
  const response = await fetch(`http://localhost:8080/api/flash-sales?customerId=${customerId.value}`)
  sales.value = await response.json()
}

async function purchase(flashSaleId) {
  const response = await fetch(`http://localhost:8080/api/flash-sales/${flashSaleId}/purchase`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ customerId: customerId.value })
  })

  if (response.ok) {
    message.value = 'Achat réussi !'
    loadSales()
  } else {
    message.value = 'Erreur lors de l\'achat'
  }
}
</script>
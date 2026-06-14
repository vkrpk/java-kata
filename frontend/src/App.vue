<template>
  <div style="max-width: 800px; margin: 0 auto; padding: 20px">
    <h1>Flash Sales</h1>

    <div>
      <label>Client :</label>
      <select v-model="selectedCustomerId" @change="loadSales">
        <option value="">-- Sélectionner un client --</option>
        <option v-for="customer in customers" :key="customer.id" :value="customer.id">
          {{ customer.email }} ({{ customer.membershipLevel }}) — score : {{ customer.priorityScore }}
        </option>
      </select>
    </div>

    <div v-if="selectedCustomerId" style="margin-top: 20px">
      <div v-for="sale in sales" :key="sale.id" style="border: 1px solid #ccc; padding: 10px; margin: 10px 0">
        <h3>{{ sale.productName }}</h3>
        <p>Stock restant : {{ sale.remainingStock }}</p>
        <p>Début : {{ new Date(sale.startTime).toLocaleTimeString() }}</p>
        <p>Fin : {{ new Date(sale.endTime).toLocaleTimeString() }}</p>
        <button @click="purchase(sale.id)" :disabled="sale.remainingStock === 0">
          {{ sale.remainingStock === 0 ? 'Stock épuisé' : 'Acheter' }}
        </button>
      </div>

      <p v-if="sales.length === 0">Aucune vente disponible pour ce client.</p>
    </div>

    <p v-if="message" style="color: green">{{ message }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const customers = ref([])
const selectedCustomerId = ref('')
const sales = ref([])
const message = ref('')

onMounted(async () => {
  const response = await fetch('http://localhost:8080/api/customers')
  customers.value = await response.json()
})

async function loadSales() {
  if (!selectedCustomerId.value) return
  const response = await fetch(`http://localhost:8080/api/flash-sales?customerId=${selectedCustomerId.value}`)
  sales.value = await response.json()
  message.value = ''
}

async function purchase(flashSaleId) {
  const response = await fetch(`http://localhost:8080/api/flash-sales/${flashSaleId}/purchase`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ customerId: selectedCustomerId.value })
  })

  if (response.ok) {
    message.value = 'Achat réussi !'
    loadSales()
  } else {
    const error = await response.json()
    message.value = error.detail || 'Erreur lors de l\'achat'
  }
}
</script>
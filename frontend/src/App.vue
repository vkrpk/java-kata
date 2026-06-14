<template>
  <div style="max-width: 800px; margin: 0 auto; padding: 20px">
    <h1>Flash Sales</h1>

    <div>
      <label>Client :</label>
      <select v-model="selectedCustomerId" @change="loadSales">
        <option value="">-- Sélectionner un client --</option>
        <option v-for="customer in customers" :key="customer.id" :value="customer.id">
          {{ customer.email }} ({{ customer.membershipLevel }})
        </option>
      </select>
      <p v-if="selectedCustomerId" style="color: orange">
        Accès anticipé aux ventes premium après 5 achats.
      </p>
    </div>

    <div v-if="selectedCustomerId" style="margin-top: 20px">
      <div v-for="sale in sales" :key="sale.id" style="border: 1px solid #ccc; padding: 10px; margin: 10px 0">
        <h3>{{ sale.productName }}</h3>
        <p>Stock restant : {{ sale.remainingStock }}</p>
        <p>Début : {{ new Date(sale.startTime).toLocaleTimeString() }}</p>
        <p>Fin : {{ new Date(sale.endTime).toLocaleTimeString() }}</p>
        <button @click="purchase(sale)" :disabled="sale.remainingStock === 0">
          {{ sale.remainingStock === 0 ? 'Stock épuisé' : 'Acheter' }}
        </button>
      </div>

      <p v-if="sales.length === 0">Aucune vente disponible pour ce client.</p>
    </div>

    <div v-if="purchases.length > 0" style="margin-top: 20px">
      <h3>Achats effectués</h3>
      <ul>
        <li v-for="(p, index) in purchases" :key="index">{{ p }}</li>
      </ul>
    </div>

    <p v-if="message" style="color: red">{{ message }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const customers = ref([])
const selectedCustomerId = ref('')
const sales = ref([])
const message = ref('')
const purchases = ref([])

onMounted(async () => {
  const response = await fetch('http://localhost:8080/api/customers')
  customers.value = await response.json()
})

async function loadSales() {
  if (!selectedCustomerId.value) return
  const response = await fetch(`http://localhost:8080/api/flash-sales?customerId=${selectedCustomerId.value}`)
  sales.value = await response.json()
}

async function purchase(sale) {
  const response = await fetch(`http://localhost:8080/api/flash-sales/${sale.id}/purchase`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ customerId: selectedCustomerId.value })
  })

  if (response.ok) {
    const response2 = await fetch('http://localhost:8080/api/customers')
    customers.value = await response2.json()
    const customerName = customers.value.find(c => c.id === selectedCustomerId.value)?.email
    purchases.value.push(`✓ ${customerName} — ${sale.productName} — ${new Date().toLocaleTimeString()}`)
    loadSales()
  } else {
    const error = await response.json()
    message.value = error.detail || 'Erreur lors de l\'achat'
  }
}
</script>
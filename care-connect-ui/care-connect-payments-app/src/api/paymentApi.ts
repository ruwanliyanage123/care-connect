export interface Invoice {
    id: number;
    patientId: number;
    totalAmount: number;
    status: string;            // PAID, PENDING, FAILED
    createdAt: string;
}

export interface Payment {
    id: number;
    invoiceId: number;
    amount: number;
    method: string;
    status: string;
    date: string;
}

const BASE_URL = "http://localhost:8082/api/v1/payments";

export async function getAllInvoices(): Promise<Invoice[]> {
    const res = await fetch(`${BASE_URL}/invoices/patient/1`);
    if (!res.ok) throw new Error("Failed to load invoices");
    return res.json();
}

export async function getPaymentsForInvoice(invoiceId: number): Promise<Payment[]> {
    const res = await fetch(`${BASE_URL}/invoice/${invoiceId}`);
    if (!res.ok) throw new Error("Failed to load payments");
    return res.json();
}



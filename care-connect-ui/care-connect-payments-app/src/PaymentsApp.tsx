import React, { useEffect, useState } from "react";
import { CreditCard, Clock, CheckCircle, Search } from "lucide-react";
import { Invoice, getAllInvoices } from "./api/paymentApi";
import "./global.css";

const PaymentsApp: React.FC = () => {
    const [invoices, setInvoices] = useState<Invoice[]>([]);
    const [loading, setLoading] = useState(false);
    const [search, setSearch] = useState("");
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const load = async () => {
            try {
                setLoading(true);
                const data = await getAllInvoices();
                setInvoices(Array.isArray(data) ? data : []);
            } catch (err) {
                console.error(err);
                setError("Failed to load payments");
            } finally {
                setLoading(false);
            }
        };

        load();
    }, []);

    const filtered = invoices.filter((inv) =>
        inv.id.toString().includes(search) ||
        (inv.status ?? "").toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div className="p-8 w-full">

            <h1 className="text-4xl font-bold mb-6 text-gray-900">Payments</h1>

            {/* Search */}
            <div className="mb-8 flex items-center gap-3 w-full md:w-1/3">
                <Search className="w-5 h-5 text-gray-500" />
                <input
                    type="text"
                    placeholder="Search by invoice or status..."
                    className="w-full px-4 py-3 rounded-xl border border-gray-300 focus:ring-2 focus:ring-blue-400 shadow-sm"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Loading */}
            {loading && <p className="text-gray-500">Loading payments...</p>}

            {/* Error */}
            {error && <p className="text-red-500">{error}</p>}

            {/* Summary cards */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">

                <div className="bg-blue-100 p-6 rounded-2xl shadow-md border border-blue-200">
                    <p className="text-gray-600">Total Invoices</p>
                    <h2 className="text-4xl font-bold">{invoices.length}</h2>
                </div>

                <div className="bg-green-100 p-6 rounded-2xl shadow-md border border-green-200">
                    <p className="text-gray-600">Paid</p>
                    <h2 className="text-4xl font-bold">
                        {invoices.filter((i) => i.status === "PAID").length}
                    </h2>
                </div>

                <div className="bg-yellow-100 p-6 rounded-2xl shadow-md border border-yellow-200">
                    <p className="text-gray-600">Pending</p>
                    <h2 className="text-4xl font-bold">
                        {invoices.filter((i) => i.status !== "PAID").length}
                    </h2>
                </div>
            </div>

            {/* Table */}
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden border border-gray-200">
                <table className="min-w-full">
                    <thead className="bg-gray-100 border-b">
                    <tr>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Invoice</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Patient ID</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Total</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Date</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Status</th>
                        <th className="px-6 py-4"></th>
                    </tr>
                    </thead>

                    <tbody>
                    {filtered.map((inv, index) => (
                        <tr
                            key={inv.id}
                            className={`transition-all ${index % 2 === 0 ? "bg-white" : "bg-gray-50"} hover:bg-blue-50`}
                        >
                            <td className="px-6 py-4 font-semibold">#{inv.id}</td>
                            <td className="px-6 py-4">{inv.patientId}</td>

                            <td className="px-6 py-4 font-semibold text-green-700">
                                Rs. {inv.totalAmount?.toLocaleString()}
                            </td>

                            <td className="px-6 py-4">
                                {inv.createdAt?.substring(0, 10)}
                            </td>

                            <td className="px-6 py-4">
                  <span
                      className={`px-3 py-1 rounded-full text-xs font-semibold
                    ${
                          inv.status === "PAID"
                              ? "bg-green-100 text-green-800"
                              : "bg-yellow-100 text-yellow-800"
                      }`}
                  >
                    {inv.status}
                  </span>
                            </td>

                            <td className="px-6 py-4 text-right">
                                <button className="px-4 py-2 text-blue-600 hover:text-blue-800 font-medium">
                                    View
                                </button>
                            </td>
                        </tr>
                    ))}

                    {filtered.length === 0 && !loading && (
                        <tr>
                            <td colSpan={7} className="text-center py-6 text-gray-500">
                                No payments found.
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PaymentsApp;

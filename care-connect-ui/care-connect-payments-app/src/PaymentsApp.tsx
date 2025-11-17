import React, { useState } from "react";
import { CreditCard, Clock, CheckCircle, XCircle, Search } from "lucide-react";
import "./global.css";

interface Payment {
    id: number;
    patient: string;
    amount: number;
    method: string;
    date: string;
    status: string;
}

const dummyPayments: Payment[] = [
    { id: 1012, patient: "John Doe", amount: 8500, method: "Credit Card", date: "2025-01-20", status: "Paid" },
    { id: 1013, patient: "Jane Smith", amount: 6200, method: "Cash", date: "2025-01-22", status: "Pending" },
    { id: 1014, patient: "Michael Lee", amount: 12000, method: "Online Transfer", date: "2025-01-21", status: "Failed" },
    { id: 1015, patient: "Emily Johnson", amount: 4500, method: "Card", date: "2025-01-23", status: "Paid" },
];

const PaymentsApp: React.FC = () => {
    const [search, setSearch] = useState("");

    const filtered = dummyPayments.filter(
        (p) =>
            p.patient.toLowerCase().includes(search.toLowerCase()) ||
            p.id.toString().includes(search)
    );

    return (
        <div className="p-8 w-full">

            {/* Page Header */}
            <h1 className="text-4xl font-bold mb-6 text-gray-900 tracking-tight">
                Payments
            </h1>

            {/* Search Bar */}
            <div className="mb-8 flex items-center gap-3 w-full md:w-1/3">
                <Search className="w-5 h-5 text-gray-500" />
                <input
                    type="text"
                    placeholder="Search by patient or invoice..."
                    className="w-full px-4 py-3 rounded-xl border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-400 shadow-sm"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Summary Cards */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">

                <div className="bg-gradient-to-br from-blue-50 to-blue-100 p-6 rounded-2xl shadow-md border border-blue-200">
                    <div className="flex items-center gap-3 mb-2">
                        <CreditCard className="text-blue-600 w-6 h-6" />
                        <p className="text-gray-600 font-medium">Total Payments</p>
                    </div>
                    <h2 className="text-4xl font-bold text-gray-800">{dummyPayments.length}</h2>
                </div>

                <div className="bg-gradient-to-br from-green-50 to-green-100 p-6 rounded-2xl shadow-md border border-green-200">
                    <div className="flex items-center gap-3 mb-2">
                        <CheckCircle className="text-green-600 w-6 h-6" />
                        <p className="text-gray-600 font-medium">Paid</p>
                    </div>
                    <h2 className="text-4xl font-bold text-gray-800">
                        {dummyPayments.filter((p) => p.status === "Paid").length}
                    </h2>
                </div>

                <div className="bg-gradient-to-br from-yellow-50 to-yellow-100 p-6 rounded-2xl shadow-md border border-yellow-200">
                    <div className="flex items-center gap-3 mb-2">
                        <Clock className="text-yellow-600 w-6 h-6" />
                        <p className="text-gray-600 font-medium">Pending</p>
                    </div>
                    <h2 className="text-4xl font-bold text-gray-800">
                        {dummyPayments.filter((p) => p.status === "Pending").length}
                    </h2>
                </div>
            </div>

            {/* Table */}
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden border border-gray-200">
                <table className="min-w-full">
                    <thead className="bg-gray-100 border-b">
                    <tr>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Invoice</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Patient</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Amount</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Method</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Date</th>
                        <th className="px-6 py-4 text-left text-xs font-semibold text-gray-600 uppercase">Status</th>
                        <th className="px-6 py-4"></th>
                    </tr>
                    </thead>

                    <tbody>
                    {filtered.map((p, index) => (
                        <tr
                            key={p.id}
                            className={`transition-all ${
                                index % 2 === 0 ? "bg-white" : "bg-gray-50"
                            } hover:bg-blue-50`}
                        >
                            <td className="px-6 py-4 font-semibold text-gray-800">
                                #{p.id}
                            </td>
                            <td className="px-6 py-4">{p.patient}</td>

                            <td className="px-6 py-4 font-semibold text-green-700">
                                Rs. {p.amount.toLocaleString()}
                            </td>

                            <td className="px-6 py-4">{p.method}</td>
                            <td className="px-6 py-4">{p.date}</td>

                            <td className="px-6 py-4">
                  <span
                      className={`px-3 py-1 rounded-full text-xs font-semibold
                      ${
                          p.status === "Paid"
                              ? "bg-green-100 text-green-800"
                              : p.status === "Pending"
                                  ? "bg-yellow-100 text-yellow-800"
                                  : "bg-red-100 text-red-800"
                      }
                    `}
                  >
                    {p.status}
                  </span>
                            </td>

                            <td className="px-6 py-4 text-right">
                                <button className="px-4 py-2 text-blue-600 hover:text-blue-800 font-medium transition">
                                    View
                                </button>
                            </td>
                        </tr>
                    ))}

                    {filtered.length === 0 && (
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

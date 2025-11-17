import React, { useState } from "react";

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

    const filtered = dummyPayments.filter((p) =>
        p.patient.toLowerCase().includes(search.toLowerCase()) ||
        p.id.toString().includes(search)
    );

    return (
        <div className="p-6 w-full">
            {/* Page Header */}
            <h1 className="text-3xl font-bold mb-4 text-gray-800">Payments</h1>

            {/* Search */}
            <div className="mb-6">
                <input
                    type="text"
                    placeholder="Search by patient or invoice number..."
                    className="w-full md:w-1/3 px-4 py-2 border rounded-xl shadow-sm focus:ring focus:ring-blue-200"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Summary Cards */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">

                <div className="bg-blue-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Total Payments</p>
                    <h2 className="text-3xl font-bold">{dummyPayments.length}</h2>
                </div>

                <div className="bg-green-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Paid</p>
                    <h2 className="text-3xl font-bold">
                        {dummyPayments.filter((p) => p.status === "Paid").length}
                    </h2>
                </div>

                <div className="bg-yellow-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Pending</p>
                    <h2 className="text-3xl font-bold">
                        {dummyPayments.filter((p) => p.status === "Pending").length}
                    </h2>
                </div>
            </div>

            {/* Payments Table */}
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden border border-gray-200">
                <table className="min-w-full table-auto">
                    <thead className="bg-gray-50">
                    <tr>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Invoice ID</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Patient</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Amount (LKR)</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Method</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Date</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Status</th>
                    </tr>
                    </thead>

                    <tbody className="text-sm text-gray-700">
                    {filtered.length === 0 ? (
                        <tr>
                            <td colSpan={6} className="text-center py-6 text-gray-500">
                                No payments found.
                            </td>
                        </tr>
                    ) : (
                        filtered.map((p, index) => (
                            <tr
                                key={p.id}
                                className={`border-b transition-all duration-200 
                    ${index % 2 === 0 ? "bg-white" : "bg-gray-50"}
                    hover:bg-blue-50 hover:cursor-pointer`}
                            >
                                <td className="px-6 py-4 font-medium text-gray-800">{p.id}</td>
                                <td className="px-6 py-4 font-semibold">{p.patient}</td>
                                <td className="px-6 py-4 font-semibold text-green-700">Rs. {p.amount.toLocaleString()}</td>
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

                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PaymentsApp;

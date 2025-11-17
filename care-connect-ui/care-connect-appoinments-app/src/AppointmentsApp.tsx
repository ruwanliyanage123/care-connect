import React, { useState } from "react";
import "./global.css";

interface Appointment {
    id: number;
    patient: string;
    consultant: string;
    date: string;
    time: string;
    status: string;
}

const dummyAppointments: Appointment[] = [
    {
        id: 1,
        patient: "John Doe",
        consultant: "Dr. Amanda Silva",
        date: "2025-01-22",
        time: "10:00 AM",
        status: "Upcoming",
    },
    {
        id: 2,
        patient: "Jane Smith",
        consultant: "Dr. Kasun Perera",
        date: "2025-01-21",
        time: "02:30 PM",
        status: "Completed",
    },
    {
        id: 3,
        patient: "Michael Lee",
        consultant: "Dr. Nethmi Fernando",
        date: "2025-01-20",
        time: "11:15 AM",
        status: "Cancelled",
    },
    {
        id: 4,
        patient: "Emily Johnson",
        consultant: "Dr. Samitha Jayawardena",
        date: "2025-01-22",
        time: "04:00 PM",
        status: "Upcoming",
    },
];

const AppointmentsApp: React.FC = () => {
    const [search, setSearch] = useState("");

    const filtered = dummyAppointments.filter((a) =>
        a.patient.toLowerCase().includes(search.toLowerCase()) ||
        a.consultant.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div className="p-6 w-full">
            {/* Page Header */}
            <h1 className="text-3xl font-bold mb-4 text-gray-800">Appointments</h1>

            {/* Search Box */}
            <div className="mb-6">
                <input
                    type="text"
                    placeholder="Search by patient or consultant..."
                    className="w-full md:w-1/3 px-4 py-2 border rounded-xl shadow-sm focus:ring focus:ring-blue-200"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Summary Stats */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
                <div className="bg-blue-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Total Appointments</p>
                    <h2 className="text-3xl font-bold">{dummyAppointments.length}</h2>
                </div>

                <div className="bg-green-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Upcoming</p>
                    <h2 className="text-3xl font-bold">
                        {dummyAppointments.filter((a) => a.status === "Upcoming").length}
                    </h2>
                </div>

                <div className="bg-red-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Cancelled</p>
                    <h2 className="text-3xl font-bold">
                        {dummyAppointments.filter((a) => a.status === "Cancelled").length}
                    </h2>
                </div>
            </div>

            {/* Appointment Table */}
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden border border-gray-200">
                <table className="min-w-full table-auto">
                    <thead className="bg-gray-50">
                    <tr>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                            ID
                        </th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                            Patient
                        </th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                            Consultant
                        </th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                            Date
                        </th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                            Time
                        </th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                            Status
                        </th>
                    </tr>
                    </thead>

                    <tbody className="text-sm text-gray-700">
                    {filtered.length === 0 ? (
                        <tr>
                            <td colSpan={6} className="text-center py-6 text-gray-500">
                                No appointments found.
                            </td>
                        </tr>
                    ) : (
                        filtered.map((a, index) => (
                            <tr
                                key={a.id}
                                className={`border-b transition-all duration-200 
                    ${index % 2 === 0 ? "bg-white" : "bg-gray-50"}
                    hover:bg-blue-50 hover:cursor-pointer`}
                            >
                                <td className="px-6 py-4 font-medium text-gray-800">{a.id}</td>
                                <td className="px-6 py-4 font-semibold">{a.patient}</td>
                                <td className="px-6 py-4">{a.consultant}</td>
                                <td className="px-6 py-4">{a.date}</td>
                                <td className="px-6 py-4">{a.time}</td>

                                <td className="px-6 py-4">
                    <span
                        className={`px-3 py-1 rounded-full text-xs font-semibold
                        ${
                            a.status === "Upcoming"
                                ? "bg-blue-100 text-blue-800"
                                : a.status === "Completed"
                                    ? "bg-green-100 text-green-800"
                                    : "bg-red-100 text-red-800"
                        }
                      `}
                    >
                      {a.status}
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

export default AppointmentsApp;

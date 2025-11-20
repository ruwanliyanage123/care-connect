import React, { useEffect, useState } from "react";
import { Consultant, getAllConsultants } from "./api/consultantApi";
import { User, CheckCircle, XCircle } from "lucide-react";

const ConsultantsApp: React.FC = () => {
    const [consultants, setConsultants] = useState<Consultant[]>([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [search, setSearch] = useState("");

    useEffect(() => {
        const loadData = async () => {
            try {
                setLoading(true);
                const data = await getAllConsultants();
                setConsultants(Array.isArray(data) ? data : []);
            } catch (err) {
                console.error(err);
                setError("Failed to load consultants from backend.");
            } finally {
                setLoading(false);
            }
        };

        loadData();
    }, []);

    const filtered = consultants.filter((c) => {
        const fullName = `${c.firstName ?? ""} ${c.lastName ?? ""}`.toLowerCase();
        return fullName.includes(search.toLowerCase());
    });

    // Stats
    const total = consultants.length;
    const active = consultants.filter((c) => c.active).length;
    const inactive = consultants.filter((c) => !c.active).length;

    return (
        <div className="p-6 w-full">
            <h1 className="text-3xl font-bold mb-6 text-gray-800">Consultants</h1>

            {/* Search */}
            <div className="mb-6">
                <input
                    type="text"
                    placeholder="Search by consultant name..."
                    className="w-full md:w-1/3 px-4 py-2 border rounded-xl shadow-sm focus:ring-blue-300"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Summary Cards */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">

                {/* Total Consultants */}
                <div className="bg-blue-100 border border-blue-200 p-5 rounded-2xl shadow-md">
                    <div className="flex items-center gap-3">
                        <User className="text-blue-700 w-6 h-6" />
                        <p className="text-gray-700 font-medium">Total Consultants</p>
                    </div>
                    <h2 className="text-4xl font-bold text-gray-900 mt-2">{total}</h2>
                </div>

                {/* Active */}
                <div className="bg-green-100 border border-green-300 p-5 rounded-2xl shadow-md">
                    <div className="flex items-center gap-3">
                        <CheckCircle className="text-green-700 w-6 h-6" />
                        <p className="text-gray-700 font-medium">Available</p>
                    </div>
                    <h2 className="text-4xl font-bold text-gray-900 mt-2">{active}</h2>
                </div>

                {/* Inactive */}
                <div className="bg-red-100 border border-red-300 p-5 rounded-2xl shadow-md">
                    <div className="flex items-center gap-3">
                        <XCircle className="text-red-700 w-6 h-6" />
                        <p className="text-gray-700 font-medium">Unavailable</p>
                    </div>
                    <h2 className="text-4xl font-bold text-gray-900 mt-2">{inactive}</h2>
                </div>
            </div>

            {loading && <p className="text-gray-500">Loading consultants...</p>}
            {error && <p className="text-red-600">{error}</p>}

            {!loading && !error && (
                <div className="bg-white shadow-xl rounded-2xl overflow-hidden border border-gray-200">
                    <table className="min-w-full">
                        <thead className="bg-gray-50">
                        <tr>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase">ID</th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Name</th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Specialization</th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Phone</th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Email</th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Experience</th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase">Status</th>
                        </tr>
                        </thead>

                        <tbody className="text-sm text-gray-700">
                        {filtered.length === 0 ? (
                            <tr>
                                <td colSpan={7} className="text-center py-6 text-gray-500">
                                    No consultants found.
                                </td>
                            </tr>
                        ) : (
                            filtered.map((c, idx) => (
                                <tr
                                    key={c.id}
                                    className={`border-b ${
                                        idx % 2 === 0 ? "bg-white" : "bg-gray-50"
                                    } hover:bg-blue-50 transition`}
                                >
                                    <td className="px-6 py-4">{c.id}</td>

                                    <td className="px-6 py-4 font-semibold">
                                        {c.firstName} {c.lastName}
                                    </td>

                                    <td className="px-6 py-4">{c.specialization}</td>

                                    <td className="px-6 py-4">{c.phone}</td>

                                    <td className="px-6 py-4">{c.email}</td>

                                    <td className="px-6 py-4">{c.yearsOfExperience} yrs</td>

                                    <td className="px-6 py-4">
                                        <span
                                            className={`px-3 py-1 text-xs rounded-full font-semibold ${
                                                c.active
                                                    ? "bg-green-100 text-green-700"
                                                    : "bg-red-100 text-red-700"
                                            }`}
                                        >
                                            {c.active ? "Active" : "Inactive"}
                                        </span>
                                    </td>
                                </tr>
                            ))
                        )}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default ConsultantsApp;

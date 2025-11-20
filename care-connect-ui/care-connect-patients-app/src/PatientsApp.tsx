import React, { useEffect, useState } from "react";
import { getAllPatients, Patient } from "./api/patientApi";

const PatientsApp: React.FC = () => {
    const [patients, setPatients] = useState<Patient[]>([]);
    const [search, setSearch] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    useEffect(() => {
        const loadPatients = async () => {
            try {
                setLoading(true);
                setError(null);

                const data = await getAllPatients();
                setPatients(Array.isArray(data) ? data : []);
            } catch (err) {
                console.error("Failed to fetch patients:", err);
                setError("Failed to load patients from backend.");
            } finally {
                setLoading(false);
            }
        };
        loadPatients();
    }, []);

    // 🔥 SAFE FILTERING (no more crash)
    const filtered = patients.filter((p) =>
        (p.name ?? "")
            .toString()
            .toLowerCase()
            .includes(search.toLowerCase())
    );

    return (
        <div className="p-6 w-full">
            {/* Page Header */}
            <h1 className="text-3xl font-bold mb-4 text-gray-800">Patients</h1>

            {/* Search Box */}
            <div className="mb-6">
                <input
                    type="text"
                    placeholder="Search patients by name..."
                    className="w-full md:w-1/3 px-4 py-2 border rounded-xl shadow-sm focus:ring focus:ring-blue-200"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Loading */}
            {loading && (
                <p className="text-gray-500 text-lg mb-4">
                    Loading patients from backend...
                </p>
            )}

            {/* Error */}
            {error && (
                <p className="text-red-600 text-lg mb-4">
                    {error}
                </p>
            )}

            {/* Table */}
            {!loading && !error && (
                <div className="bg-white shadow-xl rounded-2xl overflow-hidden border border-gray-200">
                    <table className="min-w-full table-auto">
                        <thead className="bg-gray-50">
                        <tr>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                ID
                            </th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                Name
                            </th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                Age
                            </th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                Gender
                            </th>
                            <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                Contact
                            </th>
                        </tr>
                        </thead>

                        <tbody className="text-sm text-gray-700">
                        {filtered.length === 0 ? (
                            <tr>
                                <td colSpan={5} className="text-center py-6 text-gray-500">
                                    No patients found.
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
                                    <td className="px-6 py-4 font-medium text-gray-800">
                                        {p.id ?? "-"}
                                    </td>
                                    <td className="px-6 py-4 font-semibold">
                                        {p.name ?? "N/A"}
                                    </td>
                                    <td className="px-6 py-4">
                                        {p.age ?? "N/A"}
                                    </td>
                                    <td className="px-6 py-4">
                                        {p.gender ?? "N/A"}
                                    </td>
                                    <td className="px-6 py-4">
                                        {p.contact ?? "N/A"}
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

export default PatientsApp;
